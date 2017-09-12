package info.javaway.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculateAllClass extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String fromCity=request.getParameter("fromCity");
        String toCity=request.getParameter("toCity");
        PrintWriter out = response.getWriter();
        CalculatorClass calculator=new CalculatorClass();
        calculator.startDB(fromCity,toCity,3);
        out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
        out.println("<BODY><BR>");
        out.println(calculator.getFinishCrowflight());
        out.println(calculator.getFinishMatrix());
        out.println("</BODY></HTML>");
    }
}
