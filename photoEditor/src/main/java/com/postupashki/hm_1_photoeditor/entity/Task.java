package com.postupashki.hm_1_photoeditor.entity;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class Task {
    private UUID id;
    private TaskStatusEnum taskStatus;
    private BufferedImage originalImage;
    private BufferedImage processedImage;
    private String filterType;

    public Task() {
    }

    public Task(UUID id, TaskStatusEnum taskStatus) {
        this.id = id;
        this.taskStatus = taskStatus;
    }

    public Task(UUID id, TaskStatusEnum taskStatus, BufferedImage originalImage, BufferedImage processedImage, String filterType) {
        this.id = id;
        this.taskStatus = taskStatus;
        this.originalImage = originalImage;
        this.processedImage = processedImage;
        this.filterType = filterType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(BufferedImage originalImage) {
        this.originalImage = originalImage;
    }

    public BufferedImage getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(BufferedImage processedImage) {
        this.processedImage = processedImage;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
