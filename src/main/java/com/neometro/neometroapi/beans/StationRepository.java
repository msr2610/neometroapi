package com.neometro.neometroapi.beans;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface StationRepository extends Neo4jRepository<Station, Long> {

    Station findByName(String name);
    //List<Station> findStations();
    //List<Person> findByTeammatesName(String name);
}
