package com.neometro.neometroapi.web;

import com.neometro.neometroapi.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;




@RestController
public class Neocontroller {

    private static final Logger logger = LogManager.getLogger(Neocontroller.class);

    @Autowired
    LineRepository linesRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    PathRepository pathRepository;

    @RequestMapping ("/hello")
    public String hello() {

        System.out.println("in / route of hello API");
        return "helloworld";
    } //

    @GetMapping(value="/getlineinfo")
    public Line getLineInfoJson(@RequestParam String linename) throws IllegalArgumentException {

        logger.info("In /getlineinfo; linename = " + linename);
        logger.warn("***");
        logger.warn("in /getlineinfo API call");
        logger.warn("***");

        if (linename == null || linename.isEmpty()) {

            throw new IllegalArgumentException("line name can not be null");
        }

        Line line = linesRepository.findByName(linename);

        if (line == null) {
            throw new NoSuchElementException("Line(s) data not found");
        }

        return line;
    } // end of /getLineInfojson

    @GetMapping("/getrestmetrolineslist")
    public List<Line> getRestMetroLinesList() throws IllegalArgumentException {

        String strResult = "";


        List<Line> lines = linesRepository.findAll();

        if (lines == null) {
            // return ResponseEntity.badRequest().body("No data found for any metro line");
            throw new NoSuchElementException("Line(s) data not found");
        }

        return lines;
    } // end of /getRestMetroLinesList

    @GetMapping(value="/getstationslist",produces="application/json")
    public List<String> getStationsList(@RequestParam String linename) {

        if (linename == null || linename.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Line line = linesRepository.findByName(linename);
        List<String> listStations = line.getStations();

        return listStations;

    } // end of /getStationsList


    @GetMapping("/getInterconnectedLines")
    public ResponseEntity<String> getInterconnectedLines(@RequestParam String linename) {

        String strResult = "";

        if (linename == null || linename.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input: provided metroline name is null or empty");
        }


        Line line = linesRepository.findByName(linename);
        List<String> listInterconnectedLines = line.getInterconnectedLines();

        strResult = String.join(", ", listInterconnectedLines);


        ResponseEntity<String> responseEntity = new ResponseEntity<>(strResult, HttpStatus.OK);

        return responseEntity;

    } // end of /getInterconnectedLines


    @GetMapping("/islineactive")
    public ResponseEntity<IsActiveResponse> isLineActive(@RequestParam String linename) throws IllegalArgumentException {

        String strResult = "";

        if (linename == null || linename.isEmpty()) {

            throw new IllegalArgumentException("line name can not be null");
        }

        Line line = linesRepository.findByName(linename);

        if (line == null) {
            // return ResponseEntity.badRequest().body("No data found for any metro line");
            throw new NoSuchElementException("Line(s) data not found");
        }

        if (line.getIsActive()==1) {

            IsActiveResponse response = new IsActiveResponse(Boolean.valueOf("true"));
            return ResponseEntity.ok(response);
        }
        else {
            IsActiveResponse response = new IsActiveResponse(Boolean.valueOf("false"));
            return ResponseEntity.ok(response);
        }

    } // end of /isLineActive


    @GetMapping("/isstationactive")
    public ResponseEntity<String> isStationActive(@RequestParam String stationname) {

        String strResult = "";

        if (stationname == null || stationname.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input: provided station name is null or empty");
        }


        Station station = stationRepository.findByName(stationname);

        if (station == null) {
            return ResponseEntity.badRequest().body("No data found for station " + stationname);
        }

        strResult = station.getId() + " " + station.getName();


        ResponseEntity<String> responseEntity = new ResponseEntity<>(strResult, HttpStatus.OK);

        return responseEntity;

    } // end of /isStationActive

    @GetMapping("/returnLinesOfAStation")
    public ResponseEntity<String> returnLinesOfAStation (@RequestParam String stationname) {

        String strResult = "";

        if (stationname == null || stationname.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input: provided station name is null or empty");
        }


        Station station = stationRepository.findByName(stationname);

        if (station == null) {
            return ResponseEntity.badRequest().body("No data found for station " + stationname);
        }

        List<String> lineNames = station.getLineNames();

        strResult = String.join(", ", lineNames);

        ResponseEntity<String> responseEntity = new ResponseEntity<>(strResult, HttpStatus.OK);

        return responseEntity;

    } // end of /returnLinesOfAStation

    @GetMapping("/findPath")
    public ResponseEntity<String> findPath(@RequestParam String fromstation, String tostation) {

        String strResult = "";

        if (fromstation == null || fromstation.isEmpty() || tostation == null || tostation.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input: provided station names are null or empty");
        }

        Iterable <Station> stations = pathRepository.findPathBetweenNodes(fromstation, tostation);

        if (stations == null || !stations.iterator().hasNext()) {
            return ResponseEntity.badRequest().body("No path found between stations " + fromstation + " and " + tostation );
        }

        Iterator<Station> iterator = stations.iterator();


        while (iterator.hasNext()) {
            Station station = iterator.next();
            strResult += station.getName();
            if (iterator.hasNext()) {
                if (iterator.hasNext()) strResult += " --> ";
            } // end of if
        } // end of while


        ResponseEntity<String> responseEntity = new ResponseEntity<>(strResult, HttpStatus.OK);

        return responseEntity;

    } // end of /findPath

} // end of class

