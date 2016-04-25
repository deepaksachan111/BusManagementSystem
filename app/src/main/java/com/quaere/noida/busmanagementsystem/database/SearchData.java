package com.quaere.noida.busmanagementsystem.database;

/**
 * Created by QServer on 4/23/2016.
 */
public class SearchData {

    private String source;
    private String destination;

    public SearchData(String source, String destination) {
        this.source = source;
        this.destination = destination;
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
}
