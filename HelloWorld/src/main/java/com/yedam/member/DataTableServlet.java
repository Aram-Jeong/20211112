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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/DataTableServlet")
public class DataTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DataTableServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		
		MemDAO dao = new MemDAO();
		List<DataTable> list = dao.getDataTables();
		JsonArray outer = new JsonArray(); // 밖에 있는 배열
		for(DataTable dt : list) {
			JsonArray inner = new JsonArray();
			inner.add(dt.getName());
			inner.add(dt.getPosition());
			inner.add(dt.getOffice());
			inner.add(dt.getExtn());
			inner.add(dt.getStartDate());
			inner.add(dt.getSalary());
			
			outer.add(inner);
		}
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(outer));
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
