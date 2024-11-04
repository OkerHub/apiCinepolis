package com.mx.apiCinepolis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.mx.apiCinepolis.entity.Peliculas;

public interface PeliculasRepository extends CrudRepository<Peliculas, Long> {

	
	// Encuentra una película por su nombre
    Peliculas findByNombre(String nombre);

    // Encuentra películas por su género
    List<Peliculas> findByGenero(String genero);
	
    // Método para verificar si existe una película con un nombre específico
    boolean existsByNombre(String nombre);
    
    // Método para eliminar una película por su nombre
    void deleteByNombre(String nombre);
	
	
}
