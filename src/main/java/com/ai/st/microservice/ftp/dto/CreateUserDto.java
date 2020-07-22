package com.ai.st.microservice.ftp.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CreateUserDto")
public class CreateUserDto implements Serializable {

	private static final long serialVersionUID = 7642358068586739264L;

	@ApiModelProperty(required = true, notes = "Username")
	private String username;

	@ApiModelProperty(required = true, notes = "Password")
	private String password;

	public CreateUserDto() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
