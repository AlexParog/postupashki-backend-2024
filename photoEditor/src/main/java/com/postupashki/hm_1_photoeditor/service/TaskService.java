package com.postupashki.hm_1_photoeditor.service;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;

import java.util.UUID;

/**
 * Интерфейс TaskService определяет методы для работы с задачами.
 */
public interface TaskService {

    /**
     * Возвращает статус задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return статус задачи.
     */
    GetTaskStatusResponse getStatusTaskById(UUID taskId);

    /**
     * Возвращает результат задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return DTO задачи.
     */
    TaskDto getTaskResultById(UUID taskId);

    /**
     * Создает новую задачу и возвращает её идентификатор.
     *
     * @return ответ с идентификатором новой задачи.
     */
    CreateTaskResponse createTask();
}
