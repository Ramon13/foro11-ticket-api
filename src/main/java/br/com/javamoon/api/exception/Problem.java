package br.com.javamoon.api.exception;

import java.util.List;

public class Problem {

	private Integer status;
	private List<Field> fields;
	
	public Problem(Integer status, List<Field> fields) {
		this.status = status;
		this.fields = fields;
	}

	public static class Field {
		
		private String name;
		private String message;
		
		public Field(String name, String message) {
			this.name = name;
			this.message = message;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
