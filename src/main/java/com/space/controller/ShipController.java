package com.space.controller;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Iterable<Ship> getShips(ShipFilter shipFilter, ShipDisplayOptions shipDisplayOptions) {
        return shipService.retriveShips(shipFilter, shipDisplayOptions);
    }

    @PostMapping("/ships")
    public Ship createShip(@RequestBody Ship ship) {
        if (ship == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //Если не указан isUsed, то устанавливаем ему false
        if (ship.getUsed() == null) ship.setUsed(false);

        if (!ship.isAllFieldsFull())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return shipRepository.save(ship);
    }

    @GetMapping("/ships/count")
    public long getShipsCount(ShipFilter shipFilter) {
        return shipService.retriveShipsCount(shipFilter);
    }

    @GetMapping("/ships/{id}")
    public Ship getShip(@PathVariable long id) {
        if (id < 1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Ship ship = shipService.retriveShipById(id);
        if (ship == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Ship with id=" + id + " not found"
            );
        }
        return ship;
    }

    @PostMapping("/ships/{id}")
    public Ship updateShip(@PathVariable long id, @RequestBody Ship shipParams) {
        Ship ship = getShip(id);
        return shipService.updateShip(ship, shipParams);
    }

    @DeleteMapping("/ships/{id}")
    public void deleteShip(@PathVariable long id) {
        Ship ship = getShip(id);
        shipService.deleteShip(ship);
    }
}
