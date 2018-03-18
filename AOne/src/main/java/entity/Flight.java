package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Flight {
    private int id;
    private String airplane;
    private String departureCity;
    private String arrivalCity;
    private String arrivalDate;
    private String departureDate;
    private String flightId;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "airplane")
    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    @Basic
    @Column(name = "arrival_date")
    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic
    @Column(name = "departure_date")
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Basic
    @Column(name = "fligth_id")
    public String getFligthId() {
        return flightId;
    }

    @Basic
    @Column(name = "departure_city")
    public String getDeparutreCity() {
        return departureCity;
    }

    public void setDeparutreCity(String deparutreCity) {
        this.departureCity = deparutreCity;
    }

    @Basic
    @Column(name = "arrival_city")
    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setFligthId(String fligthId) {
        this.flightId = fligthId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (airplane != null ? !airplane.equals(flight.airplane) : flight.airplane != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(flight.arrivalDate) : flight.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(flight.departureDate) : flight.departureDate != null)
            return false;
        if (flightId != null ? flightId.equals(flightId) : flight.flightId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (airplane != null ? airplane.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        return result;
    }
}
