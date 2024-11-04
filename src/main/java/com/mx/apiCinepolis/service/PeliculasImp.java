package com.mx.apiCinepolis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.apiCinepolis.entity.Peliculas;
import com.mx.apiCinepolis.repository.PeliculasRepository;

@Service
public class PeliculasImp {

	@Autowired
	PeliculasRepository peliculaRepo;

	@Transactional(readOnly = true)
	public List<Peliculas> Listar() {

		// Casteo:Un tipo de valor por otro o polimorfismo puro
		return (List<Peliculas>) peliculaRepo.findAll();

	}

	// Que no se repita el nombre
	@Transactional
	public boolean guardar(Peliculas peliculas) {
		// obtener los registros tabla
		// ciclo
		// condicion

		boolean bandera = false;
		for (Peliculas p : peliculaRepo.findAll()) {
			if (p.getNombre().equals(peliculas.getNombre())) {
				// Significa que ya existe ese registro
				bandera = true;
				break;
			}
		}

		if (bandera == false)
			peliculaRepo.save(peliculas);
		return bandera;
	}

	@Transactional(readOnly = true)
	public Peliculas buscar(Long idPeli) {
		return peliculaRepo.findById(idPeli).orElse(null);
	}

	@Transactional
	public boolean editar(Peliculas pelicula) {
		
		boolean bandera = false;
		
		for(Peliculas m : peliculaRepo.findAll()) {
			if(m.getIdPeli().equals(pelicula.getIdPeli())) {
				peliculaRepo.save(pelicula);
				bandera = true;
				break;
			}
		}
		return bandera;
	
		
	}

	public boolean eliminar(Long id) {
		boolean bandera = false;
		for (Peliculas m : peliculaRepo.findAll()) { // Cambié la forma de iteración a un bucle for-each
			if (m.getIdPeli().equals(id)) {
				peliculaRepo.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}

	// Buscar una película por nombre
	@Transactional(readOnly = true)
	public Peliculas buscarPorNombre(String nombre) {
		return peliculaRepo.findByNombre(nombre);
	}

	// Buscar películas por género
	@Transactional(readOnly = true)
	public List<Peliculas> buscarPorGenero(String genero) {
		return peliculaRepo.findByGenero(genero);
	}

}