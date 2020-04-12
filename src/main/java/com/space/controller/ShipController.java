package com.space.controller;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public @ResponseBody Iterable<Ship> getShips(ShipFilter shipFilter){
//        return shipRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
//        });
        return shipRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

//            // If designation is specified in filter, add equal where clause
//            if (filter.getDesignation() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("designation"), filter.getDesignation()));
//            }
//
//            // If firstName is specified in filter, add contains (lile)
//            // filter to where clause with ignore case
//            if (filter.getFirstName() != null) {
//                pr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
//                        "%" + filter.getFirstName().toLowerCase() + "%"));
//            }
//
//            // If lastName is specified in filter, add contains (lile)
//            // filter to where clause with ignore case
//            if (filter.getLastName() != null) {
//                pr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
//                        "%" + filter.getLastName().toLowerCase() + "%"));
//            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    @GetMapping("/ships/count")
    public long getShipsCount(){
        return shipRepository.count();
    }
}
