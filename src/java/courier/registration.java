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
public class registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String us_name = request.getParameter("txtusername");
        String us_mail = request.getParameter("txtemail");
        String us_phone = request.getParameter("txtcnumber");
        String us_pass = request.getParameter("txtpassword");

        try {
              Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
            PreparedStatement pst = con.prepareStatement("insert into user_data values(?,?,?,?)");
            pst.setString(1, us_name);
            pst.setString(2, us_phone);
            pst.setString(3, us_mail);
            pst.setString(4, us_pass);
            int row = pst.executeUpdate();
            out.println("<h1>" + row + "Inserted Succesfullyyyyy");

            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        } catch (Exception e) {
            out.println(e);
        }
    }

}
