package com.daolq.model;
import com.daolq.connection.MySQLConnection;
import com.daolq.pojo.User;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.xdevapi.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
	public User getUser(String username, String password) {
		User user = null;
		Connection con = MySQLConnection.getConnection();
		String sql = "select * from user where username = ? and password = ? ";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setAge(result.getInt("age"));
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getListUser() {
		
		List<User> users = new ArrayList<User>();
		
		Connection con = MySQLConnection.getConnection();
		String sql = "select * from user";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setAge(result.getInt("age"));
				
				users.add(user);
				
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
}
