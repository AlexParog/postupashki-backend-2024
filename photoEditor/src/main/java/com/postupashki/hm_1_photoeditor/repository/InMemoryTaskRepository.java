package com.postupashki.hm_1_photoeditor.repository;

import com.postupashki.hm_1_photoeditor.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InMemoryTaskRepository {
    Task getTaskById(UUID taskId);

    Task getTaskStatusById(UUID taskId);
}
