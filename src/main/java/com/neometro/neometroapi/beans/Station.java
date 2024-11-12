package com.neometro.neometroapi.beans;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node("Station")
public class Station {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int isActive;
    private List<String> lineName;

    private Station() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public String toString() {

        return this.id + " " + this.name;
    }

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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<String> getLineNames() {
        return lineName;
    }

    public void setLineNames(List<String> lineNames) {
        this.lineName = lineNames;
    }

    // Constructor, getters and setters
}