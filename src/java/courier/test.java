/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pratik
 */
public class test extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//
//        String ad_name = request.getParameter("txtusername");
//        String ad_pass = request.getParameter("txtpassword");
//        String ad_branch = request.getParameter("OfficeName");
        
try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery("select * from admin_data");
                while (res.next()) {
                    out.print(res.getString(1));
                }
            } catch (Exception e) {
                out.println(e);
            }
    }


}
