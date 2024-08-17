package com.postupashki.hm_1_photoeditor.mapper;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    TaskDto toTaskDto(Task task);

    Task toTask(TaskDto dto);

    CreateTaskResponse toCreateTaskResponseFromTask(Task task);

    GetTaskStatusResponse toGetTaskStatusResponseFromTask(Task task);
}
