package com.space.service;

import com.space.controller.ShipFilter;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {
    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public Iterable<Ship> retriveShips(ShipFilter filter){
        return shipRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //Если задано имя
            if(filter.getName() != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

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
}
