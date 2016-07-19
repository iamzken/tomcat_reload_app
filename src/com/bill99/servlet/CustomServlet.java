package com.bill99.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

@WebServlet(name="customMessage", urlPatterns={"/customMessage"})
public class CustomServlet extends HttpServlet
{
  private static final long serialVersionUID = -4762428211399886824L;
  
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	  
	  response.setCharacterEncoding("utf-8");
	  JSONObject result = new JSONObject();
	  result.put("resultCode", -1);
      result.put("resultMsg", "消息接收失败");
      result.put("resultCode", 1);
      result.put("resultMsg", "消息接收成功");
      response.getWriter().print(result.toJSONString());
      System.out.println(result);
	  
  }
}