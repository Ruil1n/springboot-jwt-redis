package cn.rui0.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ReturnJson {
	/**
	 * ajax返回json数据，参数重载
	 * @param response
	 * @param data 要返回的数据
	 * @param info 返回的信息
	 * @param status 返回的状态
	 * @return
	 */

	public static String jsonReturn(HttpServletResponse response,Object data,String info,int status){
//		Map<String, Object> jsonData = new HashMap<String,Object>();
//		jsonData.put("data", data);
//		jsonData.put("info", info);
//		jsonData.put("status", status);
//		render(response, JSON.toJSONString(jsonData),"json");
		render(response, jsonData(data,info,status),"json");
		return null;
	}


	/**
	 * 发送内容。使用UTF-8编码。
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response,String text,String... contentType) {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{  
	        pw.close();
	    } 
	}

	public static String jsonData(Object data,String info,int status){
		Map<String, Object> jsonData = new HashMap<String,Object>();
		jsonData.put("data", data);
		jsonData.put("info", info);
		jsonData.put("status", status);
		return JSON.toJSONString(jsonData);
	}

}
