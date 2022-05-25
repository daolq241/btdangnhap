package com.daolq.controller;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.mbeans.UserMBean;

import com.daolq.model.UserModel;
import com.daolq.pojo.User;
import com.mysql.cj.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	UserModel userModel = new UserModel();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println(username + " " + password);
		
		User user = userModel.getUser(username, password);
		System.out.println(user);
		
		if(user != null) {
			//Set Session
			HttpSession session = req.getSession();
			session.setAttribute("LOGIN_USER", user);
			session.setMaxInactiveInterval(1000);
			resp.sendRedirect(req.getContextPath() + "/profile");
		}else {
			System.out.println("Đăng nhập thất bại");
		}
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
