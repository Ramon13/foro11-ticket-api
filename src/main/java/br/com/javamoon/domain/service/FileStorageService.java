package br.com.javamoon.domain.service;

import java.io.InputStream;
import java.util.UUID;

public interface FileStorageService {

	void store(NewFile newFile);
	
	InputStream retrieve(String filename);
	
	default String generateFileName(String originalName) {
		return UUID.randomUUID() + "_" + originalName;
	}
	
	public class NewFile{
		
		private String name;
		private InputStream inStream;
		
		private NewFile(Builder builder) {
			this.name = builder.name;
			this.inStream = builder.inStream;
		}
		
		public String getName() {
			return name;
		}
		
		public InputStream getInStream() {
			return inStream;
		}
		
		public static Builder getBuilder() {
			return new Builder();
		}
		
		public static class Builder {
			private String name;
			private InputStream inStream;
			
			public Builder name(String name) {
				this.name = name;
				return this;
			}
			
			public Builder inStream(InputStream inStream) {
				this.inStream = inStream;
				return this;
			}
			
			public NewFile build() {
				return new NewFile(this);
			}
		}
	}
}
