package com.example.holaMundo;

import com.example.holaMundo.exceptions.ApiRequestExeption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service()
public class PersonaServicio {
    List<Persona> personas;

    public PersonaServicio() {
        this.personas = new ArrayList<>();
    }

    public PersonaServicio(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Persona> listarPersonas(){
        return this.personas;
    }

    public ArrayList<Persona> filtrarPersonas(String ciudad){
        return (ArrayList<Persona>) this.personas.stream().filter(p -> p.getCiudad().equals(ciudad)).collect(Collectors.toList());
    }

    public Optional<Persona> obtenerPersonaPorCedula(String cedula){
        return this.personas
                .stream()
                .filter(p-> p.getDocumento().equals(cedula))
                .findFirst();
    }

    public String obtenerNombrePorCedula(String cedula){
        Optional<Persona> persona = this.personas
                .stream()
                .filter(p-> p.getDocumento().equals(cedula))
                .findFirst();
        if(persona.isEmpty()) {
            throw new ApiRequestExeption("No se encontró la persona", 404);
        }
        return persona.get().getNombre();
    }

    public Persona crearPersona(Persona persona){
        if(persona.getNombre() == null){
            throw new ApiRequestExeption("El parametro nombre es obligatorio", 401);
        }
        this.personas.add(persona);
        return persona;
    }

    public boolean actualizarPersona(Persona persona){
        Optional<Persona> existePersona = this.obtenerPersonaPorCedula(persona.getDocumento());
        if(existePersona.isPresent()){
            Persona personaAModificar = existePersona.get();
            personaAModificar.setNombre(persona.getNombre());
            personaAModificar.setApellido(persona.getApellido());
            personaAModificar.setCiudad(persona.getCiudad());
            personaAModificar.setDocumento(persona.getDocumento());
            return true;
        }
        return false;
    }

    public boolean eliminarPersona(String cedula){
        Optional<Persona> existePersona = this.obtenerPersonaPorCedula(cedula);
        if(existePersona.isPresent()) {
            this.personas.remove(existePersona.get());
            //linea agregada
            return true;
        }
        return false;
    }
}
