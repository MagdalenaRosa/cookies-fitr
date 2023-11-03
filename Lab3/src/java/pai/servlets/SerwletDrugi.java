package pai.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
@WebServlet(name="SerwletDrugi", urlPatterns={"/SerwletDrugi"})
public class SerwletDrugi extends HttpServlet
{
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException
 {
 resp.setContentType("text/html");
 resp.setCharacterEncoding("UTF-8");
DecimalFormat df = new DecimalFormat("000000");

//Uzupełniony kod 
 var liczbaFormat = req.getAttribute("liczba"); // wymaga uzupełnienia
 PrintWriter out = resp.getWriter();
 out.println("<h1>Ten komunikat wygenerował SerwletDrugi.</h1>");
 out.println("wylosowana liczba całkowita: " + liczbaFormat);
 out.close();
 }
}