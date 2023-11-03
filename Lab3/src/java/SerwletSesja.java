import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "SerwletSesja", urlPatterns = {"/SerwletSesja"})
public class SerwletSesja extends HttpServlet {
     @Override
    public void init() throws ServletException {
        super.init();
        this.getServletContext().log("Inicjalizacja serwletu o czasie: " + new java.util.Date());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");

        var maxInactiveInterval = session.getMaxInactiveInterval();
        var currentInactiveTime = session.getMaxInactiveInterval() - session.getLastAccessedTime();

        // Sprawdzenie czasu bezczynności
        if (currentInactiveTime > 15 * 60 * 1000) { // 15 minut w milisekundach
            session.invalidate(); // Wygaśnięcie sesji
            resp.sendRedirect(req.getContextPath() + "/login.jsp"); // Przekierowanie użytkownika np. na stronę logowania
            return;
        }

        // Pobranie aktualnego czasu bezczynności sesji
        maxInactiveInterval = session.getMaxInactiveInterval();

        // Obsługa żądania zmiany czasu bezczynności
        String newInactiveInterval = req.getParameter("newInactiveInterval");
        if (newInactiveInterval != null && !newInactiveInterval.isEmpty()) {
            int newInterval = Integer.parseInt(newInactiveInterval);
            session.setMaxInactiveInterval(newInterval * 60); // Ustawienie czasu w sekundach
            maxInactiveInterval = newInterval * 60;
        }

        ArrayList<String> notes = (ArrayList<String>) session.getAttribute("notes");
        if (notes == null) {
            notes = new ArrayList<>();
            session.setAttribute("notes", notes);
        }

        String notuj = req.getParameter("notuj");
        if (notuj != null && !notuj.isEmpty())
            notes.add(notuj);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Moje notatki</h1>");
        out.println("<p>Czas bezczynności sesji (w minutach): " + (maxInactiveInterval / 60) + "</p>");
        out.println("<form action='SerwletSesja'>");
        out.println("<input type='text' name='notuj'/><br/>");
        out.println("<input type='submit' value='Dodaj'/>");
        out.println("</form>");
        out.println("<form action='SerwletSesja'>");
        out.println("<input type='text' name='newInactiveInterval' placeholder='Nowy czas bezczynności (minuty)'/>");
        out.println("<input type='submit' value='Zmień czas'/>");
        out.println("</form>");
        out.println("<ul>");
        for (String note : notes)
            out.println("<li>" + note + "</li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}
