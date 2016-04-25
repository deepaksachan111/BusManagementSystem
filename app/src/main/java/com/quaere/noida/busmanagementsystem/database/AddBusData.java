package com.quaere.noida.busmanagementsystem.database;

/**
 * Created by QServer on 4/23/2016.
 */
public class AddBusData {
    private String busname;
    private String source;
    private String destination;
    private String arrival;
    private String departure;
    private String fare;

    public AddBusData() {
    }

    public AddBusData(String source, String destination, String arrival, String departure, String fare) {
        this.source = source;
        this.destination = destination;
        this.arrival = arrival;
        this.departure = departure;
        this.fare = fare;
    }




    public AddBusData(String busname, String source, String destination, String arrival, String departure, String fare) {
        this.busname = busname;
        this.source = source;
        this.destination = destination;
        this.arrival = arrival;
        this.departure = departure;
        this.fare = fare;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }
}
