package com.mx.apiCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.apiCinepolis.entity.Peliculas;
import com.mx.apiCinepolis.service.PeliculasImp;



@RestController
@RequestMapping(path = "PeliculasWS")
@CrossOrigin
public class PeliculasWS {

	@Autowired
	PeliculasImp peliImp;

	// http://localhost:9001/PeliculasWS/listar
	@GetMapping(path = "listar")
	public List<Peliculas> listar() {
		return peliImp.Listar();

	}

	// http://localhost:9001/PeliculasWS/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula) {
		boolean respuesta = peliImp.guardar(pelicula);

		if (respuesta == true)
			return new ResponseEntity<>("Ese nombre de pelicula ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(pelicula, HttpStatus.OK);
	}

	// http://localhost:9000/PeliculasWS/buscar
	@PostMapping(path = "buscar")
	public Peliculas buscar(@RequestBody Peliculas pelicula) {
		return peliImp.buscar(pelicula.getIdPeli());

	}

	// Realizar la peticion post para editar ----- Validar que el idExista para
	// poder editar

	// http://localhost:9000/PeliculasWS/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Peliculas pelicula) {
		boolean respuesta = peliImp.editar(pelicula);

		if (respuesta == false)
			return new ResponseEntity<>("El registro no existe", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(pelicula, HttpStatus.CREATED);

	}

	// Realizar la peticion post de eliminar ---- Validar que el idExista para poder
	// eliminar
	// Eliminar una película
	// http://localhost:9000/PeliculasWS/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula) {
		boolean respuesta = peliImp.eliminar(pelicula.getIdPeli());

		if (respuesta == false)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);

	}

	// Realizar la peticion post buscar por nombre ---- 1 solo registros
	// Buscar una película por nombre
	// http://localhost:9000/PeliculasWS/buscarPorNombre
	@PostMapping("buscarPorNombre")
	public ResponseEntity<?> buscarPorNombre(@RequestBody Peliculas pelicula) {
		Peliculas peli = peliImp.buscarPorNombre(pelicula.getNombre());

		if (peli == null)
			return new ResponseEntity<>("No se encontró ninguna película con ese nombre", HttpStatus.NOT_FOUND);
		else {
			return new ResponseEntity<>(peli, HttpStatus.OK);
		}
	}

	// Realizar la peticion post buscar por genero ----- muchos registros
	// http://localhost:9000/PeliculasWS/buscarPorGenero
	// Buscar películas por género
	@PostMapping("buscarPorGenero")
	public ResponseEntity<?> buscarPorGenero(@RequestBody Peliculas pelicula) {
		List<Peliculas> peli = peliImp.buscarPorGenero(pelicula.getGenero());

		if (peli.isEmpty()) {
			return new ResponseEntity<>("No se encontraron películas con ese género", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(peli, HttpStatus.OK);
		}
	}

}