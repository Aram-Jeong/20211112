package com.yedam.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@WebServlet("/UserPostServlet")
public class UserPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserPostServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		
		MemDAO dao = new MemDAO();
		String userId = request.getParameter("user_id");
		String userName = request.getParameter("user_name");
		String email = request.getParameter("user_email");
		String phone = request.getParameter("user_phone");
		String birth = request.getParameter("user_birth");
		
		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setBirth(birth);
		
		Gson gson = new GsonBuilder().create();
		JsonObject jsonObj = new JsonObject();
		
		if(dao.insertUser(vo)) {
			jsonObj.addProperty("retCode", "OK");
			jsonObj.addProperty("userId", vo.getUserId());
			jsonObj.addProperty("userName", vo.getUserName());
			jsonObj.addProperty("email", vo.getEmail());
			jsonObj.addProperty("phone", vo.getPhone());
			jsonObj.addProperty("birth", vo.getBirth());
		}else {
			jsonObj.addProperty("retCode", "NG");
			jsonObj.addProperty("retMst", "오류 발생 !");
		}
		
		response.getWriter().println(gson.toJson(jsonObj));
				
		
		
	
	}

}
