package com.ratepay.bugtracker.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Common Response object which will be returned for all REST calls
 *
 * @param <T>
 * @author Damo
 */
public class GlobalResponseDTO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GlobalResponseDTO(T data) {
        this(data, StringUtils.EMPTY);
    }

    public GlobalResponseDTO(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
