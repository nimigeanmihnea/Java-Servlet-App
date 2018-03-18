package servlet;

import dao.FlightDAO;
import entity.Flight;
import org.hibernate.cfg.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/AddFlightServlet")
public class AddFlightServlet extends HttpServlet{

    private FlightDAO flightDAO = new FlightDAO(new Configuration().configure().buildSessionFactory());

    public AddFlightServlet(){ super(); }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            String flightId = request.getParameter("flightId");
            String airplane = request.getParameter("airplane");
            String departureCity = request.getParameter("departureCity");
            String departureDate = request.getParameter("departureDate");
            String arrivalCity = request.getParameter("arrivalCity");
            String arrivalDate = request.getParameter("arrivalDate");

            Flight flight = new Flight();

            flight.setFligthId(flightId);
            flight.setAirplane(airplane);
            flight.setDeparutreCity(departureCity);
            flight.setDepartureDate(departureDate);
            flight.setArrivalCity(arrivalCity);
            flight.setArrivalDate(arrivalDate);

            flightDAO.addFlight(flight);

            printWriter.print("Flight added!");
            response.sendRedirect("/AdminServlet");

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
