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

/**
 * Класс TaskServiceImpl реализует интерфейс TaskService для работы с задачами.
 * Содержит логику создания, обработки и получения статуса задач.
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * Логгер
     */
    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    /**
     * Репозиторий для работы с задачами
     */
    private final InMemoryTaskRepository taskRepository;
    /**
     * Маппер сущности Task
     */
    private final TaskMapper taskMapper;

    /**
     * Конструктор TaskServiceImpl, принимающий репозиторий задач и маппер.
     *
     * @param taskRepository репозиторий для хранения задач.
     * @param taskMapper     маппер для преобразования задач.
     */
    public TaskServiceImpl(InMemoryTaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Создает новую задачу и возвращает её идентификатор.
     *
     * @return ответ с идентификатором новой задачи.
     */
    @Override
    public CreateTaskResponse createTask() {
        Task task = taskRepository.createTask();

        // Имитация обработки задачи (далее будет отдельный ImageProcessor)
        simulateImageProcessing(task);

        return taskMapper.toCreateTaskResponseFromTask(task);
    }

    /**
     * Возвращает статус задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return статус задачи.
     */
    @Override
    public GetTaskStatusResponse getStatusTaskById(UUID taskId) {
        Task task = findTaskOrNotFound(taskId);
        return taskMapper.toGetTaskStatusResponseFromTask(task);
    }

    /**
     * Возвращает результат задачи по идентификатору.
     *
     * @param taskId идентификатор задачи.
     * @return DTO задачи.
     */
    @Override
    public TaskDto getTaskResultById(UUID taskId) {
        Task task = findTaskOrNotFound(taskId);
        return taskMapper.toTaskDto(task);
    }

    /**
     * Имитация обработки изображения для задачи.
     * Запускает отдельный поток, который симулирует обработку изображения на протяжении
     * случайного времени от 10 до 60 секунд. По завершении обработки устанавливает статус задачи как "готова"
     * и сохраняет задачу в репозиторий.
     *
     * @param task задача, для которой производится имитация обработки изображения.
     */
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

    /**
     * Ищет задачу по идентификатору в репозитории. Если задача не найдена, выбрасывает исключение NotFoundException.
     *
     * @param id идентификатор задачи.
     * @return найденная задача.
     * @throws NotFoundException если задача с данным идентификатором не найдена.
     */
    private Task findTaskOrNotFound(UUID id) {
        Task task = taskRepository.getTaskById(id);
        if (task == null) {
            throw new NotFoundException(String.format("Task id=%s not found!", id.toString()));
        }
        return task;
    }
}
