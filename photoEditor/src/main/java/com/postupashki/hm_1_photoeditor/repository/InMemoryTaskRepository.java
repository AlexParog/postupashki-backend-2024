package com.postupashki.hm_1_photoeditor.repository;

import com.postupashki.hm_1_photoeditor.entity.Task;

import java.util.UUID;

public interface InMemoryTaskRepository {
    Task getTaskById(UUID taskId);

    Task createTask();

    void save(Task task);
}
