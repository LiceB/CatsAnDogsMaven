package com.example.check3.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.check3.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
