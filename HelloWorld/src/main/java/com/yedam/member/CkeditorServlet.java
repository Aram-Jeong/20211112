package com.yedam.member;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/CkeditorServlet")
public class CkeditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CkeditorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String saveDir = "upload";
		ServletContext context = request.getServletContext();
		saveDir = context.getRealPath(saveDir);
		int maxSize = 10 * 1024 * 1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveDir, maxSize, encoding, policy);
		
		Enumeration names = multi.getFileNames();
		
		while(names.hasMoreElements()) {
			String name = (String) names.nextElement();
			
			String originalName = multi.getOriginalFileName(name);
			String fileSystemName = multi.getFilesystemName(name);
			String fileType = multi.getContentType(name);
			System.out.println("orginalName: " + originalName);
			System.out.println("fileSystemName: " + fileSystemName);
			System.out.println("fileType: " + fileType);
			
			String fileUrl = request.getContextPath() + "/upload/" + fileSystemName;
			
			JsonObject json = new JsonObject();
			json.addProperty("uploaded", 1); // 0 실패 1 성공
			json.addProperty("fileName", fileSystemName);
			json.addProperty("url", fileUrl);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
			
		}
	}

}
