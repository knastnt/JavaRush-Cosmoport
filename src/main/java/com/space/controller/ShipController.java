package com.space.controller;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = "application/json")
public class ShipController {
    private ShipRepository shipRepository;
    private ShipService shipService;

    public ShipController(ShipRepository shipRepository, ShipService shipService) {
        this.shipRepository = shipRepository;
        this.shipService = shipService;
    }

    @Autowired


    @GetMapping("/ships")
    public @ResponseBody Iterable<Ship> getShips(ShipFilter shipFilter, ShipDisplayOptions shipDisplayOptions){
        return shipService.retriveShips(shipFilter, shipDisplayOptions);
    }

    @GetMapping("/ships/count")
    public long getShipsCount(){
        return shipRepository.count();
    }
}
