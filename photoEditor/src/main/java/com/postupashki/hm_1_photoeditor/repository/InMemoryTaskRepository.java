package com.postupashki.hm_1_photoeditor.repository;

import com.postupashki.hm_1_photoeditor.entity.Task;

import java.util.UUID;

/**
 * Интерфейс для работы с задачами в in-memory хранилище.
 */
public interface InMemoryTaskRepository {
    /**
     * Возвращает задачу по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return объект Task, соответствующий указанному идентификатору, или null, если задача не найдена.
     */
    Task getTaskById(UUID taskId);

    /**
     * Создает новую задачу и сохраняет её в хранилище.
     *
     * @return объект Task с новым идентификатором и статусом IN_PROGRESS.
     */
    Task createTask();

    /**
     * Сохраняет или обновляет задачу в хранилище.
     *
     * @param task задача, которая будет сохранена.
     */
    void save(Task task);
}
