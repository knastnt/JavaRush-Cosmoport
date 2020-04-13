package com.space.controller;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = "application/json")
public class ShipController {
    private ShipRepository shipRepository;
    private ShipService shipService;

    @Autowired
    public ShipController(ShipRepository shipRepository, ShipService shipService) {
        this.shipRepository = shipRepository;
        this.shipService = shipService;
    }


    @GetMapping("/ships")
    public Iterable<Ship> getShips(ShipFilter shipFilter, ShipDisplayOptions shipDisplayOptions){
        return shipService.retriveShips(shipFilter, shipDisplayOptions);
    }

    @PostMapping("/ships")
    public Ship createShip(@RequestBody Ship ship){
        Ship res = shipRepository.save(ship);
        return res;
    }

    @GetMapping("/ships/count")
    public long getShipsCount(ShipFilter shipFilter){
        return shipService.retriveShipsCount(shipFilter);
    }
}
