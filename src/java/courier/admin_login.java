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
public class admin_login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ad_name = request.getParameter("txtusername");
        String ad_pass = request.getParameter("txtpassword");

      
        try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery("select * from admin_data");
                while (res.next()) {
                    String uname = res.getString(2);
                    String pass = res.getString(4);
                    if (ad_name.equals(uname) && ad_pass.equals(pass)) {
                        RequestDispatcher rd = request.getRequestDispatcher("admin_home.html");
                        rd.forward(request, response);
                    } else {
                        out.println("Invalid Credentials");
                        RequestDispatcher rd = request.getRequestDispatcher("admin_login.html");
                        rd.include(request, response);
                        break;
                    }
                }
            } catch (Exception e) {
                out.println(e);
            }
    }

}
