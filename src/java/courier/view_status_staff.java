package courier;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class view_status_staff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from status");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
            out.println("<title>Admin</title>");
            out.println("<link href=\"css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f2f2f2; }");
            out.println(".container { margin: 20px auto; padding: 30px 20px; background-color: #fff; border: 1px solid #ccc; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 850px; }"); // Increased padding top and bottom
            out.println(".container p { font-size: 16px; }"); // Increased font size for paragraph elements
            out.println(".courier-id, .status { font-size: 18px; }"); // Increased font size for courier id and status
            out.println(".th-courier-id, .th-status { font-size: 20px; }"); // Increased font size for column headers
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }"); // Added margin-top to the table
            out.println("th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".header-img { text-align: center; margin-bottom: 20px; }");
            out.println(".header-img img { width: 100%; max-height: 150px; object-fit: cover; border-top-left-radius: 5px; border-top-right-radius: 5px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"header-img\">");
            out.println("<img src=\"images/trheader.jpg\" alt=\"Header Image\">");
            out.println("</div>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th class=\"th-courier-id\">Courier ID</th>"); // Increased font size for Courier ID column header
            out.println("<th class=\"th-status\">Status</th>"); // Increased font size for Status column header
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (res.next()) {
                out.println("<tr>");
                out.println("<td class=\"courier-id\">" + res.getString(1) + "</td>");
                out.println("<td class=\"status\">" + res.getString(2) + "</td>");
                out.println("</tr>");
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
