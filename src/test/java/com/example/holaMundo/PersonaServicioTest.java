package com.example.holaMundo;

import com.example.holaMundo.exceptions.ApiRequestExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaServicioTest {

    PersonaServicio servicio;
    List<Persona> personas;

    @BeforeEach
    public void init(){
        personas = new ArrayList<>();
        servicio = new PersonaServicio(personas);
    }

    @Test
    public void agregaPersonaEnLaListaExitosamente() {
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        Assertions.assertEquals(0, personas.size());

        // Act
        servicio.crearPersona(persona);

        // Assert
        Assertions.assertEquals(1, personas.size());
    }

    @Test()
    public void fallaLaCreacionDePersonaCuandoNombreEsNulo(){
        // Arrange
        Persona persona = new Persona();

        // Assert
        Assertions.assertThrows(ApiRequestExeption.class, () -> {
            // Act
            servicio.crearPersona(persona);
        });
    }

    @Test()
    public void listarPersonasExitosamenteListaVacia(){
        // Arrange
        Assertions.assertEquals(0, personas.size());

        // Act
        personas = servicio.listarPersonas();

        // Assert
        Assertions.assertEquals(0, personas.size());
    }

    @Test()
    public void listarPersonasExitosamenteListaNoVacia(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());

        // Act
        personas = servicio.listarPersonas();

        // Assert
        Assertions.assertEquals(1, personas.size());
        Assertions.assertEquals(persona, personas.get(0));
    }

    @Test()
    public void filtrarPersonasListaVacia(){
        // Arrange
        Assertions.assertEquals(0, personas.size());

        // Act
        personas = servicio.filtrarPersonas("Medellín");

        // Assert
        Assertions.assertEquals(0, personas.size());
    }

    @Test()
    public void filtrarPersonasListaNoVaciaCiudadEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());

        // Act
        personas = servicio.filtrarPersonas("Paisalandia");

        // Assert
        Assertions.assertEquals(1, personas.size());
        Assertions.assertEquals("Paisalandia", personas.get(0).getCiudad());
    }

    @Test()
    public void filtrarPersonasListaNoVaciaCiudadNoEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());

        // Act
        personas = servicio.filtrarPersonas("Medellín");

        // Assert
        Assertions.assertEquals(0, personas.size());
    }

    @Test()
    public void obtenerPersonaPorCedulaListaVacia(){
        // Arrange
        Assertions.assertEquals(0, personas.size());
        Optional<Persona> persona;

        // Act
        persona = servicio.obtenerPersonaPorCedula("132456");

        // Assert
        Assertions.assertEquals(true, persona.isEmpty());
    }

    @Test()
    public void obtenerPersonaPorCedulaListaNoVaciaPersonaEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());
        Optional<Persona> person;

        // Act
        person = servicio.obtenerPersonaPorCedula("1152");

        // Assert
        Assertions.assertEquals(true, person.isPresent());
    }

    @Test()
    public void obtenerPersonaPorCedulaListaNoVaciaPersonaNoEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());
        Optional<Persona> person;

        // Act
        person = servicio.obtenerPersonaPorCedula("0000");

        // Assert
        Assertions.assertEquals(true, person.isEmpty());
    }

    @Test()
    public void obtenerNombrePorCedulaPersonaEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());
        String nombre;

        // Act
        nombre = servicio.obtenerNombrePorCedula("1152");

        // Assert
        Assertions.assertEquals("Lugel", nombre);
    }

    @Test()
    public void obtenerNombrePorCedulaPersonaNoEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco", "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());
        String nombre;

        // Assert
        Assertions.assertThrows(ApiRequestExeption.class, () -> {
            // Act
            servicio.obtenerNombrePorCedula("0000");
        });
    }

    @Test()
    public void actualizarPersonaPersonaEncontrada(){
        // Arrange
        Persona personaAActualizar = new Persona("Lugel", "1152", "Franco",
                "Paisalandia");
        personas.add(personaAActualizar);
        Assertions.assertEquals(1, personas.size());

        Persona personaDatosActualizados = new Persona("Lugel", "1152",
                "Franco","Medellín");
        boolean actualizacion;

        // Act
        actualizacion = servicio.actualizarPersona(personaDatosActualizados);

        // Assert
        Assertions.assertEquals("Medellín", personas.get(0).getCiudad());
        Assertions.assertEquals(true,actualizacion);
    }

    @Test()
    public void actualizarPersonaPersonaNoEncontrada(){
        // Arrange
        Persona personaAActualizar = new Persona("Lugel", "1153", "Franco",
                "Paisalandia");
        personas.add(personaAActualizar);
        Assertions.assertEquals(1, personas.size());

        Persona personaDatosActualizados = new Persona("Lugel", "1152",
                "Franco","Medellín");
        boolean actualizacion;

        // Act
        actualizacion = servicio.actualizarPersona(personaDatosActualizados);

        // Assert
        Assertions.assertEquals("Paisalandia", personas.get(0).getCiudad());
        Assertions.assertEquals(false,actualizacion);
    }

    @Test()
    public void eliminarPersonaPersonaEncontrada(){
        // Arrange
        Persona personaAEliminar = new Persona("Lugel", "1152", "Franco",
                "Paisalandia");
        personas.add(personaAEliminar);
        Assertions.assertEquals(1, personas.size());
        boolean eliminacion;

        // Act
        eliminacion = servicio.eliminarPersona("1152");

        // Assert
        Assertions.assertEquals(0, personas.size());
        Assertions.assertEquals(true,eliminacion);
    }

    @Test()
    public void eliminarPersonaPersonaNoEncontrada(){
        // Arrange
        Persona persona = new Persona("Lugel", "1152", "Franco",
                "Paisalandia");
        personas.add(persona);
        Assertions.assertEquals(1, personas.size());
        boolean eliminacion;

        // Act
        eliminacion = servicio.eliminarPersona("0000");

        // Assert
        Assertions.assertEquals(1, personas.size());
        Assertions.assertEquals(false,eliminacion);
    }

}
