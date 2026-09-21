package com.shelter.controller;

import com.shelter.model.Animal;
import com.shelter.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para el controlador AnimalController
 * Se utiliza Mockito para simular el repositorio.
 */
class AnimalControllerTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalController animalController;

    private Animal max;
    private Animal luna;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        max = new Animal();
        max.setId(1L);
        max.setNombre("Max");
        max.setEspecie("perro");
        max.setEdad(2);
        max.setAdoptado(false);

        luna = new Animal();
        luna.setId(2L);
        luna.setNombre("Luna");
        luna.setEspecie("gato");
        luna.setEdad(1);
        luna.setAdoptado(false);
    }

    @Test
    void testListarTodos() {
        when(animalRepository.findAll()).thenReturn(Arrays.asList(max, luna));
        List<Animal> resultado = animalController.listarTodos();
        assertEquals(2, resultado.size());
        assertEquals("Max", resultado.get(0).getNombre());
        assertEquals("Luna", resultado.get(1).getNombre());
    }

    @Test
    void testObtenerPorId() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(max));
        Animal resultado = animalController.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals("Max", resultado.getNombre());
        assertEquals("perro", resultado.getEspecie());
    }

    @Test
    void testCrearAnimal() {
        when(animalRepository.save(any(Animal.class))).thenReturn(max);
        Animal resultado = animalController.crearAnimal(max);
        assertNotNull(resultado);
        assertEquals("Max", resultado.getNombre());
        verify(animalRepository, times(1)).save(any(Animal.class));
    }

    @Test
    void testListarDisponibles() {
        when(animalRepository.findByAdoptadoFalse()).thenReturn(Arrays.asList(max, luna));
        List<Animal> resultado = animalController.listarDisponibles();
        assertEquals(2, resultado.size());
        verify(animalRepository, times(1)).findByAdoptadoFalse();
    }

    @Test
    void testEliminarAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(max));
        doNothing().when(animalRepository).delete(max);
        animalController.eliminarAnimal(1L);
        verify(animalRepository, times(1)).delete(max);
    }
}