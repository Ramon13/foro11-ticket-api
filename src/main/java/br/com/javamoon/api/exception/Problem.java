package br.com.javamoon.api.exception;

import java.util.List;

public class Problem {

	private Integer status;
	private String title;
	private String detail;
	private List<Field> fields;
	
	private Problem(Integer status, String title, String detail, List<Field> fields) {
		this.status = status;
		this.title = title;
		this.detail = detail;
		this.fields = fields;
	}

	public static Builder newBuilder() {
		return new Builder();
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

	public static final class Builder {
		private Integer status;
		private String title;
		private String detail;
		private List<Field> fields;
		
		private Builder() {}
		
		public Builder status(Integer status) {
			this.status = status;
			return this;
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}
		
		public Builder fields(List<Field> fields) {
			this.fields = fields;
			return this;
		}
		
		public Problem build() {
			return new Problem(status, title, detail, fields);
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
