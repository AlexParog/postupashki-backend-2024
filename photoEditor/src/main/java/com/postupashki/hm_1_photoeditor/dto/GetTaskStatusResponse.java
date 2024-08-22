package com.postupashki.hm_1_photoeditor.dto;

import com.postupashki.hm_1_photoeditor.entity.TaskStatusEnum;

/**
 * Record GetTaskStatusResponse представляет собой ответ на запрос статуса задачи.
 */
public record GetTaskStatusResponse(TaskStatusEnum taskStatusEnum) {
}
