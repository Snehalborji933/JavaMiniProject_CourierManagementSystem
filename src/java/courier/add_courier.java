package courier;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class add_courier extends HttpServlet {
    

 public static String generateRandomPassword(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
 
        return IntStream.range(0, len).map(i -> random.nextInt(chars.length())).mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex))).collect(Collectors.joining());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String receiver_name = request.getParameter("Receivername");
        String phone = request.getParameter("Receiverphone");
        String courier_type = request.getParameter("Shiptype");
        String weight = request.getParameter("Weight");
        String qty = request.getParameter("Qnty");
        String mode = request.getParameter("Mode");
        String branch = request.getParameter("branch");
        String pickup_date = request.getParameter("Packupdate");
        String address = request.getParameter("Receiveraddress");
        
        String courier_id = generateRandomPassword(10);
        
        
        
        try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier", "root", "root");
                    PreparedStatement pst = con.prepareStatement("insert into courier_data values(?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, courier_id);
                    pst.setString(2, receiver_name);
                    pst.setString(3, phone);
                    pst.setString(4, address);
                    pst.setString(5, courier_type);
                    pst.setString(6, weight);
                    pst.setString(7, qty);
                    pst.setString(8, branch);
                    pst.setString(9, pickup_date);
                    pst.setString(10, mode);
                    int row = pst.executeUpdate();
                    out.println("<h1>" + "Your Order Has been Booked Successfully!!!");
                    out.println("Your Tracking ID: "+ courier_id);
                    out.println("<a href=\"add_courier.html\"> Go Back </>");
                } catch (Exception e) {
                    out.println(e);
                }
//                RequestDispatcher rd = request.getRequestDispatcher("add_courier.html");
//                rd.forward(request, response);
            }
    }

