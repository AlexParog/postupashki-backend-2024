package com.postupashki.hm_1_photoeditor.controller;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST-контроллер обрабатывает HTTP-запросы, связанные с задачами.
 * Позволяет создавать новую задачу, получать статус задачи и результат обработки.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    /**
     * Сервис задач
     */
    private final TaskService taskService;

    /**
     * Конструктор TaskController, принимающий сервис для работы с задачами.
     *
     * @param taskService сервис для обработки задач.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Обрабатывает POST-запрос на создание новой задачи.
     *
     * @return ответ с идентификатором созданной задачи.
     */
    @Operation(summary = "Создание новой задачи")
    @ApiResponse(responseCode = "201", description = "Задача успешно создана")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponse createNewTask() {
        return taskService.createTask();
    }

    /**
     * Обрабатывает GET-запрос на получение статуса задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return ответ со статусом задачи.
     */
    @Operation(summary = "Получение статуса задачи по ID")
    @ApiResponse(responseCode = "200", description = "Статус задачи успешно получен")
    @GetMapping("/status/{taskId}")
    public GetTaskStatusResponse getStatusTask(@PathVariable UUID taskId) {
        return taskService.getStatusTaskById(taskId);
    }

    /**
     * Обрабатывает GET-запрос на получение результата задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return DTO с данными задачи.
     */
    @Operation(summary = "Получение результата задачи по ID")
    @ApiResponse(responseCode = "200", description = "Результат задачи успешно получен")
    @GetMapping("/result/{taskId}")
    public TaskDto getTaskResult(@PathVariable UUID taskId) {
        return taskService.getTaskResultById(taskId);
    }

}
