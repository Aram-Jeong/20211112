package com.yedam.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/fileUploadServlet")
public class fileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public fileUploadServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		// System.out.println(request.getParameter("author"));
		
		int maxSize = 1024 * 1024 * 10;
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("images");
	
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		String prod_item = multi.getParameter("prod_item");
		String prod_desc = multi.getParameter(" prod_desc");
		String like_it = multi.getParameter("like_it");
		String origin_price = multi.getParameter("origin_price");
		String sale_price = multi.getParameter("sale_price");
		String prod_image = multi.getFilesystemName("prod_image");	
		
		ItemVO vo = new ItemVO();
		vo.setProdItem(prod_item);
		vo.setProdDesc(prod_desc);
		vo.setLikeIt(Double.parseDouble(like_it));
		vo.setOriginPrice(Integer.parseInt(origin_price));
		vo.setSalePrice(Integer.parseInt(sale_price));
		vo.setProdImage(prod_image);
		
		
		System.out.println("실제위치: " + saveDir);
		
		PrintWriter out = response.getWriter();
	
		// 파일: 서버, 사용자 입력값: db 저장
		MemDAO dao = new MemDAO();
		dao.uploadProduct(vo);
		
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(vo)); 
	}

}
