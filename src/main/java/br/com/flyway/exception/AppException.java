package br.com.flyway.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String object;
	private String field;
	private String message;

	public AppException(HttpStatus status, String object, String field, String message) {

		if (status == null) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		if (object == null) {
			object = "";
		}

		this.status = status;
		this.object = object;
		this.field = field;
		this.message = message;
	}

	public AppException(String object, String field, String message) {
		this(null, object, field, message);
	}

	public AppException(String field, String message) {
		this(null, null, field, message);
	}

	public AppException(String message) {
		this((String) null, message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
