package com.example.holaMundo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/personas")
public class PersonaController {

    PersonaServicio service;

    @Autowired()
    private PersonaController(PersonaServicio service){
        this.service = service;

    }

    @GetMapping()
    public List<Persona> listarPersonas(){
        return this.service.listarPersonas();
    }

    @GetMapping("filter")
    public ArrayList<Persona> filtrarPersonas(@RequestParam("ciudad") String ciudad){
        return this.service.filtrarPersonas(ciudad);
    }

    @GetMapping("{cedula}")
    public Optional<Persona> obtenerPersonaPorCedula(@PathVariable("cedula") String cedula){
        return this.service.obtenerPersonaPorCedula(cedula);
    }

    @GetMapping("nombre/{cedula}")
    public String obtenerNombrePorCedula(@PathVariable("cedula") String cedula){
        return this.service.obtenerNombrePorCedula(cedula);
    }

    @PostMapping()
    public Persona crearPersona(@RequestBody Persona persona){
        return this.service.crearPersona(persona);
    }

    @PutMapping()
    public boolean actualizarPersona(@RequestBody Persona persona){
        return this.service.actualizarPersona(persona);
    }

    @DeleteMapping("{cedula}")
    public boolean actualizarPersona(@PathVariable("cedula") String cedula){
        return this.service.eliminarPersona(cedula);
    }
}
