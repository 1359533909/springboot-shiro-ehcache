package com.mxl.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
	private int id;
	private String username;
	private String password;
	private String perms;
}
