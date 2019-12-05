package com.venilry.security.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class HttpServletRequestPost {
	public static void authenticationAuthenticate(HttpServletResponse response,String url,Map<String, String> parameters) throws IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println(" <HEAD>");
        out.println(" <meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
        out.println(" <TITLE>loading</TITLE>");
        out.println(" <meta http-equiv=\"Content-Type\" content=\"text/html charset=GBK\">\n");
        out.println(" </HEAD>");
        out.println(" <BODY>");
        out.println("<form name=\"submitForm\" action=\"" + url + "\" method=\"post\">");
        Iterator<String> it = parameters.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            out.println("<input type=\"hidden\" name=\"" + key + "\" value=\"" + parameters.get(key) + "\"/>");
        }
        out.println("</from>");
        out.println("<script>window.document.submitForm.submit();</script> ");
        out.println(" </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
	}
}
