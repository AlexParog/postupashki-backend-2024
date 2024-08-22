package com.postupashki.hm_1_photoeditor.mapper;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Интерфейс для преобразования объектов Task в различные DTO и обратно.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    /**
     * Преобразует объект Task в объект TaskDto.
     *
     * @param task задача, которая будет преобразована.
     * @return объект TaskDto, содержащий данные задачи.
     */
    @Mapping(target = "id", expression = "java(task.getId())")
    @Mapping(target = "taskStatus", expression = "java(task.getTaskStatus())")
    @Mapping(target = "originalImage", expression = "java(task.getOriginalImage())")
    @Mapping(target = "processedImage", expression = "java(task.getProcessedImage())")
    @Mapping(target = "filterType", expression = "java(task.getFilterType())")
    TaskDto toTaskDto(Task task);

    /**
     * Преобразует объект TaskDto обратно в объект Task.
     *
     * @param dto объект TaskDto, который будет преобразован.
     * @return объект Task, соответствующий данным из TaskDto.
     */
    Task toTask(TaskDto dto);

    /**
     * Преобразует объект Task в объект CreateTaskResponse.
     *
     * @param task задача, которая будет преобразована.
     * @return объект CreateTaskResponse с идентификатором задачи.
     */
    @Mapping(target = "taskId", expression = "java(task.getId())")
    CreateTaskResponse toCreateTaskResponseFromTask(Task task);

    /**
     * Преобразует объект Task в объект GetTaskStatusResponse.
     *
     * @param task задача, которая будет преобразована.
     * @return объект GetTaskStatusResponse с текущим статусом задачи.
     */
    @Mapping(target = "taskStatusEnum", expression = "java(task.getTaskStatus())")
    GetTaskStatusResponse toGetTaskStatusResponseFromTask(Task task);
}
