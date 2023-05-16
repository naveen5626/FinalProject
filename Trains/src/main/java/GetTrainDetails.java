import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/GetTrainDetails")
public class GetTrainDetails extends HttpServlet {
    private static final long serialVersionUID = 1 ;

    String dns = "ec2-3-133-84-78.us-east-2.compute.amazonaws.com";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTrainDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Fetch Employee Details";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
        		"<html>\n" +
                "<head>\n" +
                "    <title style=\"color: #333; font-size: 24px;\">Train Details</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #CCCFF;\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "            margin-top: 50px;\n" +
                "            margin-bottom: 50px;\n" +
                "            font-size: 36px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Train Details</h1>\n" +
                "</body>\n" +
                "</html>");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dns + ":3306/myDB", "naveen", "naveen");
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");

        sql = "SELECT * FROM trains WHERE train_number=?";
        try {

            statement1 = connection.prepareStatement(sql);
            String theUserName = keyword;
            statement1.setString(1, theUserName);
     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //out.println("<table border=1 width=50% height=30%>");
        //out.println("<tr><th>TrainNumber</th><th>TrainName</th><th>StartCity</th><th>EndCity</th><th>SeatCapacity</th></tr>");
        

        try {
            while (rs.next()) {
                //Retrieve by column name
                String number = rs.getString("train_number");
                String name = rs.getString("train_name");
                String start = rs.getString("starting_city");
                String end = rs.getString("destination");
                String seat = rs.getString("seat_capacity");
                //out.println("<tr><td>" + number + "</td><td>" + name + "</td><td>" + start + "</td><td>" + end + "</td><td>" + seat + "</td></tr>");
                out.println("<table style=\"border-collapse: collapse; width: 50%; height: 30%; margin: auto;\">\n" +
                        "<thead style=\"background-color: #333; color: #fff;\">\n" +
                        "<tr><th style=\"padding: 10px; border: 1px solid #ddd;\">Train Number</th>\n" +
                        "<th style=\"padding: 10px; border: 1px solid #ddd;\">Train Name</th>\n" +
                        "<th style=\"padding: 10px; border: 1px solid #ddd;\">Start City</th>\n" +
                        "<th style=\"padding: 10px; border: 1px solid #ddd;\">End City</th>\n" +
                        "<th style=\"padding: 10px; border: 1px solid #ddd;\">Seat Capacity</th>\n" +
                        "</tr>\n" +
                        "</thead>\n" +
                        "<tbody style=\"text-align: center;\">\n" +
                        "<tr><td style=\"padding: 10px; border: 1px solid #ddd;\">" +number+ "</td>\n" +
                        "<td style=\"padding: 10px; border: 1px solid #ddd;\">" +name+"</td>\n" +
                        "<td style=\"padding: 10px; border: 1px solid #ddd;\">" +start+"</td>\n" +
                        "<td style=\"padding: 10px; border: 1px solid #ddd;\">" +end+ "</td>\n" +
                        "<td style=\"padding: 10px; border: 1px solid #ddd;\">" +seat+ "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>");

               }
            out.println("</body></html>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}