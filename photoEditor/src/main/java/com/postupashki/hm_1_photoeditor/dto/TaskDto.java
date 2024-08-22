package com.postupashki.hm_1_photoeditor.dto;

import com.postupashki.hm_1_photoeditor.entity.TaskStatusEnum;

import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * Record TaskDto представляет данные задачи, включая изображения и тип фильтра.
 */
public record TaskDto(UUID id, TaskStatusEnum taskStatus, BufferedImage originalImage, BufferedImage processedImage,
                      String filterType) {
}
