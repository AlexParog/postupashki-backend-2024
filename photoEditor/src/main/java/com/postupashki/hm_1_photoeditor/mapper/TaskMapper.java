package com.postupashki.hm_1_photoeditor.mapper;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    @Mapping(target = "id", expression = "java(task.getId())")
    @Mapping(target = "taskStatus", expression = "java(task.getTaskStatus())")
    @Mapping(target = "originalImage", expression = "java(task.getOriginalImage())")
    @Mapping(target = "processedImage", expression = "java(task.getProcessedImage())")
    @Mapping(target = "filterType", expression = "java(task.getFilterType())")
    TaskDto toTaskDto(Task task);

    Task toTask(TaskDto dto);

    @Mapping(target = "taskId", expression = "java(task.getId())")
    CreateTaskResponse toCreateTaskResponseFromTask(Task task);

    @Mapping(target = "taskStatusEnum", expression = "java(task.getTaskStatus())")
    GetTaskStatusResponse toGetTaskStatusResponseFromTask(Task task);
}
