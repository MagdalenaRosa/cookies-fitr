package pai.servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;
@WebServlet(name="SerwletPierwszy", urlPatterns={"/SerwletPierwszy"})
public class SerwletPierwszy extends HttpServlet
{
 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException
 {
 resp.setContentType("text/html");
 resp.setCharacterEncoding("UTF-8");
ServletContext sc = this.getServletContext();

//Przekierowanie 
 RequestDispatcher dispatcher = sc.getRequestDispatcher("/SerwletDrugi");
 
 // Uzupełniony kod 
 Random los = new Random();
 int calkLos = los.nextInt(999999);
 req.setAttribute("liczba", calkLos);
 PrintWriter out = resp.getWriter();
 out.println("<html>");
 out.println("<body>");
// out.println("<h1>Ten komunikat wygenerował SerwletPierwszy.</h1>");
 dispatcher.include(req, resp);
 }
}