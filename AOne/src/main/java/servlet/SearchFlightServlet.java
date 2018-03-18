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

@WebServlet("/SearchFlightServlet")
public class SearchFlightServlet extends HttpServlet{

    private String saveSearch;
    private FlightDAO flightDAO = new FlightDAO(new Configuration().configure().buildSessionFactory());

    public SearchFlightServlet(){ super(); }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
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

                request.getRequestDispatcher("search.html").include(request, response);
            }else {
                printWriter.print("This flight does not exist!");
                request.getRequestDispatcher("search.html").include(request, response);
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
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();

            Flight flight = flightDAO.findFlightByFlightId(saveSearch);

            if (flight != null) {
                flightDAO.deleteFlight(flight);
                request.getRequestDispatcher("search.html").include(request, response);
            } else {
                printWriter.print("This flight does not exist!");
                request.getRequestDispatcher("search.html").include(request, response);
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
