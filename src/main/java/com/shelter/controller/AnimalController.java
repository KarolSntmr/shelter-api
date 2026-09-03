package com.shelter.controller;

import com.shelter.model.Animal;
import com.shelter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    // GET - Listar todos los animales
    @GetMapping
    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    // GET - Listar solo animales disponibles
    @GetMapping("/disponibles")
    public List<Animal> listarDisponibles() {
        return animalRepository.findByAdoptadoFalse();
    }

    // GET - Obtener un animal por ID
    @GetMapping("/{id}")
    public Animal obtenerPorId(@PathVariable Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));
    }

    // POST - Crear un nuevo animal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal crearAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // PUT - Actualizar un animal
    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id, @RequestBody Animal animalActualizado) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));

        animal.setNombre(animalActualizado.getNombre());
        animal.setEspecie(animalActualizado.getEspecie());
        animal.setEdad(animalActualizado.getEdad());
        animal.setAdoptado(animalActualizado.getAdoptado());

        return animalRepository.save(animal);
    }

    // DELETE - Eliminar un animal
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAnimal(@PathVariable Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + id));
        animalRepository.delete(animal);
    }
}