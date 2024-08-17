package com.postupashki.hm_1_photoeditor.repository.impl;

import com.postupashki.hm_1_photoeditor.entity.Task;
import com.postupashki.hm_1_photoeditor.entity.TaskStatusEnum;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTaskRepositoryImpl implements InMemoryTaskRepository {

    private static Map<UUID, Task> database = new ConcurrentHashMap<>();

    @Override
    public Task getTaskById(UUID taskId) {
        return database.get(taskId);
    }

    @Override
    public Task createTask() {
        Task newTask = new Task();
        newTask.setId(UUID.randomUUID());
        newTask.setTaskStatus(TaskStatusEnum.IN_PROGRESS);

        save(newTask);
        return newTask;
    }

    @Override
    public void save(Task task) {
        database.put(task.getId(), task);
    }
}
