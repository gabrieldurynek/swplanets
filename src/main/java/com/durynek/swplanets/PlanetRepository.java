package com.durynek.swplanets;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanetRepository extends CrudRepository<Planet, Integer> {
    List<Planet> findByNameLike(String name);
    List<Planet> findAll();
}