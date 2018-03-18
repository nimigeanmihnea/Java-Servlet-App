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
import java.util.Vector;

@WebServlet("/UpdateFlightServlet")
public class UpdateFlightServlet extends HttpServlet{

    private String saveSearch;
    private FlightDAO flightDAO = new FlightDAO(new Configuration().configure().buildSessionFactory());

    public UpdateFlightServlet(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            String search = request.getParameter("search");
            saveSearch = search;

            Flight flight = flightDAO.findFlightByFlightId(search);

            if(flight != null) {
                HtmlUtils htmlUtils = new HtmlUtils();

                printWriter.print(htmlUtils.getTableHead("center", 1));

                printWriter.print(htmlUtils.getTH("center", "Flight ID"));
                printWriter.print(htmlUtils.getTH("center", "Airplane"));
                printWriter.print(htmlUtils.getTH("center", "Departure City"));
                printWriter.print(htmlUtils.getTH("center", "Departure Date and Time"));
                printWriter.print(htmlUtils.getTH("center", "Arrival City"));
                printWriter.print(htmlUtils.getTH("center", "Arrival Date and Time"));

                Vector vector = new Vector();

                vector.addElement(flight.getFligthId());
                vector.addElement(flight.getAirplane());
                vector.addElement(flight.getDeparutreCity());
                vector.addElement(flight.getDepartureDate());
                vector.addElement(flight.getArrivalCity());
                vector.addElement(flight.getArrivalDate());

                printWriter.print(htmlUtils.getTableContents("center", vector, 6));

                request.getRequestDispatcher("update_flight.html").include(request, response);
            }else {
                printWriter.print("This flight does not exist!");
                request.getRequestDispatcher("update_flight.html").include(request, response);
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();

            Flight flight = flightDAO.findFlightByFlightId(saveSearch);

            if (flight != null) {
                String airplane = request.getParameter("airplane");
                String departureCity = request.getParameter("departureCity");
                String departureDate = request.getParameter("departureDate");
                String arrivalCity = request.getParameter("arrivalCity");
                String arrivalDate = request.getParameter("arrivalDate");

                if(!airplane.equals("")){
                    flight.setAirplane(airplane);
                }

                if(!departureCity.equals("")){
                    flight.setDeparutreCity(departureCity);
                }

                if(!departureDate.equals("")){
                    flight.setDepartureDate(departureDate);
                }

                if(!arrivalCity.equals("")){
                    flight.setArrivalCity(arrivalCity);
                }

                if(!arrivalDate.equals("")){
                    flight.setArrivalDate(arrivalDate);
                }

                flightDAO.updateFlight(flight);

                request.getRequestDispatcher("update_flight.html").include(request, response);
            } else {
                printWriter.print("This flight does not exist!");
                request.getRequestDispatcher("update_flight.html").include(request, response);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
