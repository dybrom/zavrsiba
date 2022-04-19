package com.example.fixba.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private String message;
	private LocalDateTime timestamp;
	private int code;
	private String path;
	private String type;

	@Override
	public String toString() {
		return "ExceptionResponse{" +
						"message='" + message + '\'' +
						", timestamp=" + timestamp +
						", code=" + code +
						", path='" + path + '\'' +
						", type='" + type + '\'' +
						'}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
