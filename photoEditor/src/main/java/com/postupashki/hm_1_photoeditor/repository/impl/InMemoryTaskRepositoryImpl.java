package com.postupashki.hm_1_photoeditor.repository.impl;

import com.postupashki.hm_1_photoeditor.entity.Task;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;

import java.util.UUID;

public class InMemoryTaskRepositoryImpl implements InMemoryTaskRepository {
    @Override
    public Task getTaskById(UUID taskId) {
        return null;
    }

    @Override
    public Task getTaskStatusById(UUID taskId) {
        return null;
    }
}
