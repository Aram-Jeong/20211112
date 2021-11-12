package com.yedam.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/GetItemOneServlet")
public class GetItemOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetItemOneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		
		String prodId = request.getParameter("prod_id");
		MemDAO dao = new MemDAO();
		ItemVO vo = dao.searchItem(prodId);
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(vo));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
