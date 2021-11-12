package com.yedam.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@WebServlet("/userDataServlet")
public class UserGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserGetServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/json;charset=UTF-8");
		MemDAO dao = new MemDAO();
		List<UserVO> userList = dao.getUserList();
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(userList));
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
	
	
	}

}
