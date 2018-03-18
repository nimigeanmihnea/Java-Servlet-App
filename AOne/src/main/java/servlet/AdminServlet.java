package servlet;

import dao.FlightDAO;
import entity.Flight;
import misc.HtmlUtils;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private FlightDAO flightDAO = new FlightDAO(new Configuration().configure().buildSessionFactory());

    public AdminServlet(){ super(); }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            List<Flight> flights = flightDAO.findFlights();

            HtmlUtils htmlUtils = new HtmlUtils();

            printWriter.print(htmlUtils.getTableHead("center", 1));

            printWriter.print(htmlUtils.getTH("center", "Flight ID"));
            printWriter.print(htmlUtils.getTH("center", "Airplane"));
            printWriter.print(htmlUtils.getTH("center", "Departure City"));
            printWriter.print(htmlUtils.getTH("center", "Departure Date and Time"));
            printWriter.print(htmlUtils.getTH("center", "Arrival City"));
            printWriter.print(htmlUtils.getTH("center", "Arrival Date and Time"));

            Vector vector = new Vector();
            for (Flight flight: flights) {
                vector.addElement(flight.getFligthId());
                vector.addElement(flight.getAirplane());
                vector.addElement(flight.getDeparutreCity());
                vector.addElement(flight.getDepartureDate());
                vector.addElement(flight.getArrivalCity());
                vector.addElement(flight.getArrivalDate());
            }

            printWriter.print(htmlUtils.getTableContents("center", vector, 6));

            request.getRequestDispatcher("admin.html").include(request, response);

        }catch (IOException e){
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

}
