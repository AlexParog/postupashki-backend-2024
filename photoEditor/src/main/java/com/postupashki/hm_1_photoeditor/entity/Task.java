package com.postupashki.hm_1_photoeditor.entity;

import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * Класс Task представляет задачу по обработке изображения.
 */
public class Task {
    /**
     * Идентификатор задачи
     */
    private UUID id;
    /**
     * Статус задачи
     */
    private TaskStatusEnum taskStatus;
    /**
     * Исходное изображение
     */
    private BufferedImage originalImage;
    /**
     * Обработанное изображение
     */
    private BufferedImage processedImage;
    /**
     * Тип фильтра
     */
    private String filterType;

    /**
     * Конструктор по умолчанию.
     */
    public Task() {
    }

    /**
     * Конструктор для создания задачи с заданным идентификатором и статусом.
     *
     * @param id         уникальный идентификатор задачи.
     * @param taskStatus статус задачи.
     */
    public Task(UUID id, TaskStatusEnum taskStatus) {
        this.id = id;
        this.taskStatus = taskStatus;
    }

    /**
     * Конструктор для создания задачи с полным набором параметров.
     *
     * @param id             уникальный идентификатор задачи.
     * @param taskStatus     статус задачи.
     * @param originalImage  исходное изображение.
     * @param processedImage обработанное изображение.
     * @param filterType     тип примененного фильтра.
     */
    public Task(UUID id, TaskStatusEnum taskStatus, BufferedImage originalImage, BufferedImage processedImage, String filterType) {
        this.id = id;
        this.taskStatus = taskStatus;
        this.originalImage = originalImage;
        this.processedImage = processedImage;
        this.filterType = filterType;
    }

    /**
     * Геттер идентификатора
     *
     * @return идентификатор задачи
     */
    public UUID getId() {
        return id;
    }

    /**
     * Сеттер идентификатора
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Геттер статуса задачи
     *
     * @return статус задачи
     */
    public TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    /**
     * Сеттер статуса задачи
     */
    public void setTaskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Геттер исходного изображения
     *
     * @return исходное изображение
     */
    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    /**
     * Сеттер исходного изображения
     */
    public void setOriginalImage(BufferedImage originalImage) {
        this.originalImage = originalImage;
    }

    /**
     * Геттер обработанного изображения
     *
     * @return исходное изображение
     */
    public BufferedImage getProcessedImage() {
        return processedImage;
    }

    /**
     * Сеттер обработанного изображения
     */
    public void setProcessedImage(BufferedImage processedImage) {
        this.processedImage = processedImage;
    }

    /**
     * Геттер фильтра
     *
     * @return фильтр
     */
    public String getFilterType() {
        return filterType;
    }

    /**
     * Сеттер фильтра
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
