package com.neometro.neometroapi.beans;

//import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.query.Query;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PathRepository extends Neo4jRepository<Station, Long> {

    @Query("MATCH path = shortestPath((startNode)-[*..10]->(endNode)) WHERE startNode.name = $fromstation AND endNode.name = $tostation RETURN path")
    Iterable<Station> findPathBetweenNodes(String fromstation, String tostation);

    //@Query("MATCH (n1:Station {name: $station1})-[r:NEXT_STATION]->(n2:Station {name: $station2}) RETURN r")
    //Next_Station findRelationshipBetween(String station1, String station2);
}

//MATCH path = shortestPath((startNode)-[*..10]->(endNode)) WHERE startNode.name = 'Andheri' AND endNode.name = 'Aarey Colony'

//MATCH path = shortestPath((startNode)-[*..10]->(endNode)) WHERE startNode.name = $fromstation AND endNode.name = $tostation


//@Query("MATCH path = shortestPath((start:NodeEntity {name: $startNode})-[*..10]->(end:NodeEntity {name: $endNode})) RETURN path")
