/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pratik
 */
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String us_name = request.getParameter("txtusername");
        String us_pass = request.getParameter("txtpassword");

        try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery("select * from user_data");
                while (res.next()) {
                    String uname = res.getString(1);
                    String pass = res.getString(4);
                    if (us_name.equals(uname) && us_pass.equals(pass)) {
                        RequestDispatcher rd = request.getRequestDispatcher("customer_home.html");
                        rd.forward(request, response);
                    } else {
                        out.println("Invalid Credentials");
                        RequestDispatcher rd = request.getRequestDispatcher("login.html");
                        rd.include(request, response);
                        break;
                    }
                }
            } catch (Exception e) {
                out.println(e);
            }

    }

}
