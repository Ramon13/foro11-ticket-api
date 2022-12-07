package br.com.javamoon.domain.model;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Image {

	@Column(nullable = false, length = 128)
	private String name;
	
	@Column(name = "content_type",nullable = false, length = 64)
	private String contentType;
	
	@Column(nullable = false)
	private Long size;
	
	private transient InputStream inStream;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public InputStream getInStream() {
		return inStream;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}
}
