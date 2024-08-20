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

@SpringBootTest
@AutoConfigureMockMvc
class PhotoEditorApplicationTests {

    private final String RANDOM_UUID = "hi, i`m wrong uuid";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    InMemoryTaskRepository taskRepository;

    @Test
    public void testTaskSuccessfulCreation() throws Exception {
        MvcResult result = mockMvc.perform(post("/task"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.taskId").exists())
                .andReturn();

        String taskId = JsonPath.read(result.getResponse().getContentAsString(), "$.taskId");
        assertNotNull(taskId);
    }

    @Test
    public void testGetTaskStatusAndTaskResult() throws Exception {
        // создать задачу
        MvcResult mvcResultCreateTask = mockMvc.perform(post("/task"))
                .andExpect(status().isCreated())
                .andReturn();

        final String taskId = JsonPath.read(mvcResultCreateTask.getResponse().getContentAsString(), "$.taskId");
        Task task = taskRepository.getTaskById(UUID.fromString(taskId));
        assertNotNull(task, "Task was not found in repository after creation");

        MvcResult mvcResultGetTaskStatus = mockMvc.perform(get("/status/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskStatusEnum").exists())
                .andDo(print())
                .andReturn();

        final String taskStatus = JsonPath.read(mvcResultGetTaskStatus.getResponse().getContentAsString(), "$.taskStatusEnum");
        assertEquals(task.getTaskStatus().toString(), taskStatus);

        // проверить что result существует и статус в результате READY
        MvcResult mvcResultGetResult = mockMvc.perform(get("/result/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskStatusEnum").value("READY"))
                .andReturn();

        final String resultStatus = JsonPath.read(mvcResultGetResult.getResponse().getContentAsString(), "$.taskStatusEnum");
        assertEquals(task.getTaskStatus().toString(), resultStatus);
    }

    @Test
    public void testFindTaskStatusWithRandomUUID() throws Exception {
        mockMvc.perform(
                        get("/status/").param(RANDOM_UUID))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Objects.requireNonNull(mvcResult.getResolvedException())
                        .getClass()
                        .equals(NotFoundException.class));
    }

    @Test
    public void testFindTaskResultWithRandomUUID() throws Exception {
        mockMvc.perform(
                        get("/result/").param(RANDOM_UUID))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Objects.requireNonNull(mvcResult.getResolvedException())
                        .getClass()
                        .equals(NotFoundException.class));
    }
}
