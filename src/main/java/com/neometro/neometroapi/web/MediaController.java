package com.neometro.neometroapi.web;

import com.neometro.neometroapi.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MediaController {

    @GetMapping(value="/myperson")
    public Person getMethod1() {

        System.out.println("in method1 ");

        Person p = new Person();
        p.setId("102");
        p.setLname("Risbud");
        p.setFname("Advay");

        return p;

    } // end of /getLineInfojson

    @PostMapping(value="/postperson")
    public String postMethod2(@RequestBody Person person) {
       /*{
           "id": "102",
               "fname": "Advay",
               "lname": "Risbud"
       } */
       System.out.println("inside method222 ");
       System.out.println("person details " + person.getId() + " " + person.getFname());
       return "person data posted!";
    } // end of /getrestmetrolineslist

}