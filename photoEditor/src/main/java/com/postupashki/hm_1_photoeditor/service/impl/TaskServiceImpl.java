package com.postupashki.hm_1_photoeditor.service.impl;

import com.postupashki.hm_1_photoeditor.dto.CreateTaskResponse;
import com.postupashki.hm_1_photoeditor.dto.GetTaskStatusResponse;
import com.postupashki.hm_1_photoeditor.dto.TaskDto;
import com.postupashki.hm_1_photoeditor.entity.Task;
import com.postupashki.hm_1_photoeditor.entity.TaskStatusEnum;
import com.postupashki.hm_1_photoeditor.exception.NotFoundException;
import com.postupashki.hm_1_photoeditor.mapper.TaskMapper;
import com.postupashki.hm_1_photoeditor.repository.InMemoryTaskRepository;
import com.postupashki.hm_1_photoeditor.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final InMemoryTaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(InMemoryTaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public CreateTaskResponse createTask() {
        Task task = taskRepository.createTask();

        // Имитация обработки задачи (далее будет отдельный ImageProcessor)
        simulateImageProcessing(task);

        return taskMapper.toCreateTaskResponseFromTask(task);
    }

    @Override
    public GetTaskStatusResponse getStatusTaskById(UUID taskId) {
        Task task = findTaskOrNotFound(taskId);
        return taskMapper.toGetTaskStatusResponseFromTask(task);
    }

    @Override
    public TaskDto getTaskResultById(UUID taskId) {
        Task task = findTaskOrNotFound(taskId);
        return taskMapper.toTaskDto(task);
    }

    private void simulateImageProcessing(Task task) {
        new Thread(() -> {
            try {
                int randomTimeInSeconds = ThreadLocalRandom.current().nextInt(10, 61);
                int sleepTimeInMillis = randomTimeInSeconds * 1000;
                // Имитация времени обработки
                Thread.sleep(sleepTimeInMillis); // от 10 до 60 секунд
                // Установка статуса задачи как завершенной
                task.setTaskStatus(TaskStatusEnum.READY);
                taskRepository.save(task);
                logger.info("Task {} saved with status {}", task.getId(), task.getTaskStatus());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }


    private Task findTaskOrNotFound(UUID id) {
        Task task = taskRepository.getTaskById(id);
        if (task == null) {
            throw new NotFoundException(String.format("Task с id=%s не найден!", id.toString()));
        }
        return task;
    }
}
