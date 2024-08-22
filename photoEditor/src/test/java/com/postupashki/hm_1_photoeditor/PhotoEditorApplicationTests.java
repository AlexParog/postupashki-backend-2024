package com.postupashki.hm_1_photoeditor;

import com.jayway.jsonpath.JsonPath;
import com.postupashki.hm_1_photoeditor.entity.Task;
import com.postupashki.hm_1_photoeditor.exception.NotFoundException;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестовый класс для проверки работы REST API приложения PhotoEditor.
 * Использует MockMvc для эмуляции HTTP-запросов и проверки ответов.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PhotoEditorApplicationTests {

    /**
     * Некорректный UUID, используемый для тестов с несуществующими задачами.
     */
    private final String RANDOM_UUID = "hi-im-wrong-uuid";

    /**
     * MockMvc, используемый для выполнения HTTP-запросов в тестах.
     */
    @Autowired
    MockMvc mockMvc;

    /**
     * Репозиторий задач, используемый для проверки состояния задач в тестах.
     */
    @Autowired
    InMemoryTaskRepository taskRepository;

    /**
     * Тест проверяет успешное создание задачи через API.
     * Выполняется POST-запрос к /task, затем проверяется, что задача была создана и вернулся корректный идентификатор задачи.
     *
     * @throws Exception если что-то пошло не так при выполнении запроса.
     */
    @Test
    public void testTaskSuccessfulCreation() throws Exception {
        MvcResult result = mockMvc.perform(post("/task"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.taskId").exists())
                .andReturn();

        String taskId = JsonPath.read(result.getResponse().getContentAsString(), "$.taskId");
        assertNotNull(taskId);
    }

    /**
     * Тест проверяет получение статуса и результата задачи.
     * Сначала создается задача, затем проверяется её статус и результат через соответствующие API-запросы.
     *
     * @throws Exception если что-то пошло не так при выполнении запросов.
     */
    @Test
    public void testGetTaskStatusAndTaskResult() throws Exception {
        // создать задачу
        MvcResult mvcResultCreateTask = mockMvc.perform(post("/task"))
                .andExpect(status().isCreated())
                .andReturn();

        final String taskId = JsonPath.read(mvcResultCreateTask.getResponse().getContentAsString(), "$.taskId");
        Task task = taskRepository.getTaskById(UUID.fromString(taskId));
        assertNotNull(task, "Task was not found in repository after creation");

        MvcResult mvcResultGetTaskStatus = mockMvc.perform(get("/task/status/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskStatusEnum").exists())
                .andDo(print())
                .andReturn();

        final String taskStatus = JsonPath.read(mvcResultGetTaskStatus.getResponse().getContentAsString(), "$.taskStatusEnum");
        assertEquals(task.getTaskStatus().toString(), taskStatus);

        // проверить что result существует и статус в результате READY
        MvcResult mvcResultGetResult = mockMvc.perform(get("/task/result/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskStatus").exists())
                .andReturn();

        final String resultStatus = JsonPath.read(mvcResultGetResult.getResponse().getContentAsString(), "$.taskStatus");
        assertEquals(task.getTaskStatus().toString(), resultStatus);
    }

    /**
     * Тест проверяет обработку запроса на получение статуса задачи с использованием некорректного UUID.
     * Ожидается, что API вернет ошибку 404 и выбросит исключение NotFoundException.
     *
     * @throws Exception если что-то пошло не так при выполнении запроса.
     */
    @Test
    public void testFindTaskStatusWithRandomUUID() throws Exception {
        mockMvc.perform(
                        get("/status/", RANDOM_UUID))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Objects.requireNonNull(mvcResult.getResolvedException())
                        .getClass()
                        .equals(NotFoundException.class));
    }

    /**
     * Тест проверяет обработку запроса на получение результата задачи с использованием некорректного UUID.
     * Ожидается, что API вернет ошибку 404 и выбросит исключение NotFoundException.
     *
     * @throws Exception если что-то пошло не так при выполнении запроса.
     */
    @Test
    public void testFindTaskResultWithRandomUUID() throws Exception {
        mockMvc.perform(
                        get("/result/", RANDOM_UUID))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Objects.requireNonNull(mvcResult.getResolvedException())
                        .getClass()
                        .equals(NotFoundException.class));
    }
}
