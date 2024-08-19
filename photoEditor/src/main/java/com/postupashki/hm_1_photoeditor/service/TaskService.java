package com.postupashki.hm_1_photoeditor.service;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;

import java.util.UUID;

public interface TaskService {

    GetTaskStatusResponse getStatusTaskById(UUID taskId);

    TaskDto getTaskResultById(UUID taskId);

    CreateTaskResponse createTask();
}
