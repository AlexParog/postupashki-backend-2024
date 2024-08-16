package com.postupashki.hm_1_photoeditor.service.impl;

import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;
import com.postupashki.hm_1_photoeditor.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final InMemoryTaskRepository taskRepository;

    public TaskServiceImpl(InMemoryTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public GetTaskStatusResponse getStatusTaskById(UUID taskId) {

        return null;
    }

    @Override
    public TaskDto getTaskResultById(UUID taskId) {
        return null;
    }
}
