package br.com.javamoon.api.model;

public class UserDTO {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDTO [name=" + name + "]";
	}
}
