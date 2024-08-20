package com.postupashki.hm_1_photoeditor.controller;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponse createNewTask() {
        return taskService.createTask();
    }

    @GetMapping("/status/{taskId}")
    public GetTaskStatusResponse getStatusTask(@PathVariable UUID taskId) {
        return taskService.getStatusTaskById(taskId);
    }

    @GetMapping("/result/{taskId}")
    public TaskDto getTaskResult(@PathVariable UUID taskId) {
        return taskService.getTaskResultById(taskId);
    }

}
