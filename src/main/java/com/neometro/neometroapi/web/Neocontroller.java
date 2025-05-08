package com.neometro.neometroapi.web;

import com.neometro.neometroapi.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@RestController
@RequestMapping("/metro")
public class Neocontroller {

    private static final Logger logger = LogManager.getLogger(Neocontroller.class);

    @Autowired
    LineRepository linesRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    PathRepository pathRepository;

    @GetMapping("/hello")
    public String hello() {
        return "helloworld";
    }

    @GetMapping("/lines")
    public ResponseEntity<List<Line>> getAllMetroLines() {
        List<Line> lines = linesRepository.findAll();
        if (lines == null || lines.isEmpty()) {
            throw new NoSuchElementException("Line(s) data not found");
        }
        return ResponseEntity.ok(lines);
    }

    //@Operation(summary = "Get line info", description = "Fetches details for a metro line by name")
    //@ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/lines/{lineName}")
    public ResponseEntity<Line> getLineInfo(@PathVariable String lineName) {
        Line line = linesRepository.findByName(lineName);
        if (line == null) {
            throw new NoSuchElementException("Line not found: " + lineName);
        }
        return ResponseEntity.ok(line);
    }

    @GetMapping("/lines/{lineName}/stations")
    public ResponseEntity<List<String>> getStationsForLine(@PathVariable String lineName) {
        Line line = linesRepository.findByName(lineName);
        if (line == null) {
            throw new NoSuchElementException("Line not found: " + lineName);
        }
        return ResponseEntity.ok(line.getStations());
    }

    @GetMapping("/lines/{lineName}/interconnections")
    public ResponseEntity<List<String>> getInterconnectedLines(@PathVariable String lineName) {
        Line line = linesRepository.findByName(lineName);
        if (line == null) {
            throw new NoSuchElementException("Line not found: " + lineName);
        }
        return ResponseEntity.ok(line.getInterconnectedLines());
    }

    @GetMapping("/lines/{lineName}/status")
    public ResponseEntity<IsActiveResponse> isLineActive(@PathVariable String lineName) {
        Line line = linesRepository.findByName(lineName);
        if (line == null) {
            throw new NoSuchElementException("Line not found: " + lineName);
        }
        boolean isActive = line.getIsActive() == 1;
        return ResponseEntity.ok(new IsActiveResponse(isActive));
    }

    @GetMapping("/stations/{stationName}/status")
    public ResponseEntity<String> isStationActive(@PathVariable String stationName) {
        Station station = stationRepository.findByName(stationName);
        if (station == null) {
            return ResponseEntity.badRequest().body("No data found for station " + stationName);
        }
        return ResponseEntity.ok(station.getId() + " " + station.getName() + " " + station.getIsActive());
    }

    @GetMapping("/stations/{stationName}/lines")
    public ResponseEntity<List<String>> getLinesForStation(@PathVariable String stationName) {
        Station station = stationRepository.findByName(stationName);
        if (station == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        return ResponseEntity.ok(station.getLineNames());
    }

    @GetMapping("/route")
    public ResponseEntity<PathResponse> findRoute(@RequestParam String from, @RequestParam String to) {
        if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
            return ResponseEntity.badRequest().body(new PathResponse(List.of("Invalid input: station names cannot be empty.")));
        }

        Iterable<Station> stations = pathRepository.findPathBetweenNodes(from, to);

        if (stations == null || !stations.iterator().hasNext()) {
            return ResponseEntity.badRequest().body(new PathResponse(List.of("No path found between stations " + from + " and " + to)));
        }

        List<String> path = new ArrayList<>();
        stations.forEach(s -> path.add(s.getName()));
        return ResponseEntity.ok(new PathResponse(path));
    }
}
