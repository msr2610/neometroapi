package com.neometro.neometroapi.beans;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends Neo4jRepository<Line, Long> {


    //@Query("MATCH (l:Line {name: $name}) RETURN l")
    Line findByName(@Param("name") String name);

    //Line findByName(String name);

    //List<Station> findStations();
    //List<Person> findByTeammatesName(String name);
}

