package com.neometro.neometroapi.beans;

import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Line {

    @Id @GeneratedValue private Long id;

    private String name;

    private String colorcode;

    private int isActive;

    private int numberOfStations;

    private int distanceCovered;

    private List<String> stations;

    private List<String> interconnectedLines;

    private int totalStationCount;

    //@Relationship(type = "belongs_to", direction = Relationship.Direction.INCOMING)
    //private List<Station> stations;

    private Line() {
        // Empty constructor required as of Neo4j API 2.0.5
    };


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public void setNumberOfStations(int numberOfStations) {
        this.numberOfStations = numberOfStations;
    }

    public int getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(int distanceCovered) {
        this.distanceCovered = distanceCovered;
    }




    public List<String> getStations() {
        return stations;
    }


    public void setStations(List<String> stations) {
        this.stations = stations;
    }


    public List<String> getInterconnectedLines() {
        return interconnectedLines;
    }


    public void setInterconnectedLines(List<String> interconnectedLines) {
        this.interconnectedLines = interconnectedLines;
    }


    public int getTotalStationCount() {
        return totalStationCount;
    }


    public void setTotalStationCount(int totalStationCount) {
        this.totalStationCount = totalStationCount;
    }

    /*
    @Override
    public String toString() {
        return "Line [id=" + id + ", name=" + name + ", colorcode=" + colorcode + ", isActive=" + isActive
                + ", numberOfStations=" + numberOfStations + ", distanceCovered=" + (distanceCovered != null ? distanceCovered.toString() : "")  + ", stations="
                + stations + ", interconnectingLines=" + interconnectingLines + ", totalStationCount="
                + totalStationCount + "]";
    }
    */

    @Override
    public String toString() {
        return "Line [id=" + id + ", name=" + name + ", colorcode=" + colorcode + ", isActive=" + isActive
                + ", numberOfStations=" + numberOfStations + ", distanceCovered=0" + ", stations="
                + stations + ", interconnectedLines=" + interconnectedLines + ", totalStationCount="
                + totalStationCount + "]";
    }
}

