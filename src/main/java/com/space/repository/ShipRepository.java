package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface ShipRepository extends CrudRepository<Ship, Long>, JpaSpecificationExecutor<Ship> {
}
