package io.docker;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(value = "/")
	public String displayMessage(HttpServletRequest request) {
		StringBuilder response = new StringBuilder();

		response.append("<html>");

		printRemoteParameters(request, response);
		buildHtml(request, response, request.getHeaderNames(), "Header");
		buildHtml(request, response, request.getParameterNames(), "Parameter");
		buildHtml(request, response, request.getAttributeNames(), "Attribute");
		buildHtml(request, response, request.getCookies(), "Cookie");

		response.append("</html>");
		return response.toString();
	}

	private void printRemoteParameters(HttpServletRequest request,
	        StringBuilder response) {
		response.append("<table border=1><tr><td>Remote Param Name</td><td>Remote Param Value</td></tr>");
		response.append("<tr><td>").append("Remote Address: ")
		        .append("</td><td>").append(request.getRemoteAddr())
		        .append("</td></tr>");
		response.append("<tr><td>").append("Remote Host: ").append("</td><td>")
		        .append(request.getRemoteHost()).append("</td></tr>");
		response.append("<tr><td>").append("Remote Port: ").append("</td><td>")
		        .append(request.getRemotePort()).append("</td></tr>");
		response.append("<tr><td>").append("Remote User: ").append("</td><td>")
		        .append(request.getRemoteUser()).append("</td></tr>");
		
		response.append("<tr><td>").append("Server Name: ").append("</td><td>")
		        .append(request.getServerName()).append("</td></tr>");
		response.append("<tr><td>").append("Server Port: ").append("</td><td>")
		        .append(request.getServerPort()).append("</td></tr>");
		response.append("</table>");
	}

	private void buildHtml(HttpServletRequest request, StringBuilder response,
	        Enumeration<String> parameters, String tableName) {
		if (parameters == null || parameters.hasMoreElements() == false)
			return;

		response.append("<p>").append(tableName)
		        .append("s</p><table border=1><tr><td>").append(tableName)
		        .append(" Name</td><td>").append(tableName)
		        .append(" Value</td></tr>");
		while (parameters.hasMoreElements()) {
			String header = (String) parameters.nextElement();
			try {
				Method method = request.getClass().getMethod("get" + tableName,
				        String.class);
				String paramValue = String.valueOf(method.invoke(request,
				        header));
				response.append("<tr><td>").append(header).append("</td><td>")
				        .append(paramValue).append("</td></tr>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.append("</table>");
	}

	private void buildHtml(HttpServletRequest request, StringBuilder response,
	        Cookie[] cookies, String tableName) {
		if (cookies == null || cookies.length == 0)
			return;

		response.append("<html><p>").append(tableName)
		        .append("s</p><table border=1><tr><td>").append(tableName)
		        .append(" Name</td><td>").append(tableName)
		        .append(" Value</td></tr>");
		for (Cookie cookie : cookies) {
			response.append("<tr><td>").append(cookie.getName())
			        .append("</td><td>").append(cookie.getValue())
			        .append("</td></tr>");
		}
		response.append("</table>");
	}
}
