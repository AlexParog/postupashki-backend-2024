package com.postupashki.hm_1_photoeditor.repository.impl;

import com.postupashki.hm_1_photoeditor.entity.Task;
import com.postupashki.hm_1_photoeditor.entity.TaskStatusEnum;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация интерфейса InMemoryTaskRepository, использующая ConcurrentHashMap для хранения задач.
 */
@Repository
public class InMemoryTaskRepositoryImpl implements InMemoryTaskRepository {

    /**
     * Внутреннее хранилище задач, использующее ConcurrentHashMap для потокобезопасности.
     */
    private static Map<UUID, Task> database = new ConcurrentHashMap<>();

    /**
     * Возвращает задачу по её идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return объект Task, соответствующий указанному идентификатору, или null, если задача не найдена.
     */
    @Override
    public Task getTaskById(UUID taskId) {
        return database.get(taskId);
    }

    /**
     * Создает новую задачу с уникальным идентификатором и статусом IN_PROGRESS,
     * затем сохраняет её в хранилище.
     *
     * @return созданная задача.
     */
    @Override
    public Task createTask() {
        Task newTask = new Task();
        newTask.setId(UUID.randomUUID());
        newTask.setTaskStatus(TaskStatusEnum.IN_PROGRESS);

        save(newTask);
        return newTask;
    }

    /**
     * Сохраняет или обновляет задачу в хранилище.
     *
     * @param task задача, которая будет сохранена.
     */
    @Override
    public void save(Task task) {
        database.put(task.getId(), task);
    }
}
