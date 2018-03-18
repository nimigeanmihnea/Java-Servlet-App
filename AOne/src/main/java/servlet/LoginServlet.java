package servlet;



import dao.UserDAO;
import entity.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO(new Configuration().configure().buildSessionFactory());

    public LoginServlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userDAO.findByUsername(username);

            if (user != null && user.getPassword().equals(password)){
                if(user.getRole().equals("user")){
                    printWriter.print("You are successfully logged as user");
                    Cookie cookie = new Cookie("username", username);
                    response.addCookie(cookie);
                    response.sendRedirect("/UserServlet");
                }else {
                    printWriter.print("You are successfully logged as admin");
                    Cookie cookie = new Cookie("username", username);
                    response.addCookie(cookie);
                    response.sendRedirect("/AdminServlet");
                }
            }else{
                printWriter.print("Log in error!");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e){
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
