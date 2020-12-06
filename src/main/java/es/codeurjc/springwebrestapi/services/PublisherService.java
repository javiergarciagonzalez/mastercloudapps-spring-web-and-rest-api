package es.codeurjc.springwebrestapi.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    ArrayList<String> publishers;

    public PublisherService() {
        this.publishers = new ArrayList<String>();
        publishers.add("Red circle");
        publishers.add("Blue circle");
        publishers.add("Green circle");
        publishers.add("Yellow circle");
    }

    public ArrayList<String> findAll() {
        return this.publishers;
    }
}
