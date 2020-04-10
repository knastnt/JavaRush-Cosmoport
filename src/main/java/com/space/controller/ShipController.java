package com.space.controller;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = "application/json")
public class ShipController {
    private ShipRepository shipRepository;

    @Autowired
    public ShipController(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @GetMapping("/ships")
    public Iterable<Ship> getShips(){
        return shipRepository.findAll();
    }

    @GetMapping("/ships/count")
    public long getShipsCount(){
        Iterator<Ship> ships = getShips().iterator();
        
        long i = 0;
        while(ships.hasNext()) {
            i++;
            ships.next();
        }
        return i;
    }
}
