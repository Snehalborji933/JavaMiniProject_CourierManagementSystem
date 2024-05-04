package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pratik
 */
public class add_status extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String staff_name = request.getParameter("staff");
        String courier_id = request.getParameter("courierid");

        if (staff_name.equals("1") || staff_name.equals("2") || staff_name.equals("3") || staff_name.equals("4") || staff_name.equals("5")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
                PreparedStatement pst = con.prepareStatement("insert into status (courier_id, status) values (?,?)");
                pst.setString(1, courier_id);

                String staffAssigned = "";
                switch (staff_name) {
                    case "1":
                        staffAssigned = "Lokesh";
                        break;
                    case "2":
                        staffAssigned = "Tanya";
                        break;
                    case "3":
                        staffAssigned = "Pratham";
                        break;
                    case "4":
                        staffAssigned = "Shashank";
                        break;
                    case "5":
                        staffAssigned = "Pranav";
                        break;
                    default:
                        break;
                }

                pst.setString(2, "Staff Assigned to " + staffAssigned);
                int row = pst.executeUpdate();
                if (row > 0) {
                    out.println("Staff Assigned");
                } else {
                    out.println("Failed to assign staff");
                }
                con.close();
            } catch (Exception e) {
                out.println(e);
            }
        } else {
            out.println("Invalid staff selection");
        }
    }
}
