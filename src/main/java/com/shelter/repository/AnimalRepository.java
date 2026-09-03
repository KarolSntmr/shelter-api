package com.shelter.repository;

import com.shelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // Método para buscar animales no adoptados
    List<Animal> findByAdoptadoFalse();
}