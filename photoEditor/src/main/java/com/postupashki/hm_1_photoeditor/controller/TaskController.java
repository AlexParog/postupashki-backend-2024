package com.postupashki.hm_1_photoeditor.controller;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.service.TaskService;
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
    @GetMapping("/result/{taskId}")
    public TaskDto getTaskResult(@PathVariable UUID taskId) {
        return taskService.getTaskResultById(taskId);
    }

}
