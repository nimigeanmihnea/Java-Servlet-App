package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{

    public LogoutServlet(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            request.getRequestDispatcher("login.html").include(request, response);

            Cookie ck = new Cookie("username","");
            ck.setMaxAge(0);
            response.addCookie(ck);

            printWriter.print("You are successfully logged out!");

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
