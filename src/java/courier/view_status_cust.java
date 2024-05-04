package courier;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class view_status_cust extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String courier_id = request.getParameter("courierid");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from status");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Courier Status</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f2f2f2; }");
            out.println(".container { margin: 20px auto; padding: 20px; background-color: #fff; border: 1px solid #ccc; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 800px; }");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("h2 { color: white; background-color: #007bff; padding: 10px; border-radius: 5px; }"); // Apply blue box and white text to h2
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class=\"container\">");
            out.println("<h2>Courier Status</h2>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Staff Name</th>");
            out.println("<th>Status</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (res.next()) {
                if (res.getString(1).equals(courier_id)) {
                    out.println("<tr>");
                    out.println("<td>" + res.getString(1) + "</td>");
                    out.println("<td>" + res.getString(2) + "</td>");
                    out.println("</tr>");
                }
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(e);
        }
    }
}
