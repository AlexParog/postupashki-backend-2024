package com.postupashki.hm_1_photoeditor.exception;

/**
 * Исключение, выбрасываемое в случае, если задача не найдена.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Конструктор, принимающий сообщение об ошибке.
     *
     * @param message сообщение об ошибке.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
