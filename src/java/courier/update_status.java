package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class update_status extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String courier_id = request.getParameter("courierid");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
            PreparedStatement pst = connection.prepareStatement("UPDATE status SET Status=? WHERE courier_id=?");
            pst.setString(1, status);
            pst.setString(2, courier_id);
            int result = pst.executeUpdate();
            if (result > 0) {
                out.println("Courier status updated successfully");
            } else {
                out.println("Failed to update courier status");
            }
            connection.close();
        } catch (Exception e) {
            out.println(e);
        }

    }
}
