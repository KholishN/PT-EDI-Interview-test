package com.pt.edi.dto;

import java.util.List;
import java.util.Map;

public class DtoResponse<T> {
	private String status;
	private List<T> data;
	private Map<String, Object> message;

	public DtoResponse(String status, List<T> data, Map<String, Object> message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Map<String, Object> getMessage() {
		return message;
	}

	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}

}
