package info.javaway.web.servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListCityClass extends HttpServlet {
    DataSource dataSource=null;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private InitialContext initialContext;

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        PrintWriter out = response.getWriter();
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/magenta/datasource/test-distance-calculator");
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from city");
            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
            out.println("<BODY><BR>");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                out.println(name);
            }
            out.println("<tr><td><form action=index.jsp method=post><input type=submit value=nazad></form></td></tr>");
            out.println("</BODY></HTML>");

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }
}
