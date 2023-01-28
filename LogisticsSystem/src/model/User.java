package model;

import java.util.List;

public class User {
	
	public User(String username) {
		this.userName = username;
	}
	public String userName;
	public List<Order> orders;
	

}
