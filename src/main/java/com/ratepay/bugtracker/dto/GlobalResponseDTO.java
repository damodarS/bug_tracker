package com.ratepay.bugtracker.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Common Response object which will be returned for all REST calls
 * 
 * @author Damo
 *
 * @param <T>
 */
public class GlobalResponseDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String message;
	private T data;
	private List<String> messages;
	private String error;

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

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public GlobalResponseDTO(T data) {
		this(data, StringUtils.EMPTY);
	}

	public GlobalResponseDTO(T data, String message) {
		this(data, StringUtils.isNotEmpty(message) ? Arrays.asList(message) : null);
	}

	public GlobalResponseDTO(T data, List<String> messages) {
		this.data = data;
		this.messages = messages;
	}

}
