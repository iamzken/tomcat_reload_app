package com.bill99.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="MessageForwardServlet", urlPatterns={"/message/forward"})
public class ForwardServlet extends HttpServlet
{
  private static final long serialVersionUID = -4762428211399886824L;
  
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	  
	  request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
	  String requestUrl="http://mcip-inner.99bill.com/coe-mcip-mgw/customMessage.do";
    String result = "";
    try {
      String params = request.getParameter("params");
      try {
    	  System.out.println("/message/forward to \"" + requestUrl + "\": " + params);
          byte[] data = params.getBytes("UTF-8");
          URL realUrl = new URL(requestUrl);
          URLConnection conn = realUrl.openConnection();
          conn.setRequestProperty("User-Agent", request.getHeader("User-Agent"));
          conn.setRequestProperty("Accept", request.getHeader("Accept"));
          conn.setRequestProperty("Accept-Language", request.getHeader("Accept-Language"));
          conn.setRequestProperty("Accept-Encoding", request.getHeader("Accept-Encoding"));
          conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
          conn.setRequestProperty("Connection", request.getHeader("Connection"));
          conn.setRequestProperty("Pragma", request.getHeader("Pragma"));
          conn.setRequestProperty("Cache-Control", request.getHeader("Cache-Control"));
          conn.setConnectTimeout(30000);
          conn.setReadTimeout(30000);
          conn.setDoOutput(true);
          conn.setDoInput(true);

          OutputStream out = null;
          try {
            out = conn.getOutputStream();
            out.write(data);
            out.flush();
          } catch (Exception e) {
            throw e;
          } finally {
            if (out != null)
              out.close();
          }

          BufferedReader in = null;
          try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
              result = result + line;
            }

            result = new String(result);
          } catch (Exception e) {
            throw e;
          } finally {
            if (in != null) {
              in.close();
            }
          }
          System.out.println("/message/forward recv: " + result);
        } catch (Exception e) {
        	e.printStackTrace();
        }	
    } catch (Exception e) {
      e.printStackTrace();
      result = "{\"resultCode\":-1,\"resultMsg\":\"无法转发\"}";
    } finally {
      System.out.println(result);
      response.getWriter().write(result);
    }
  }
}