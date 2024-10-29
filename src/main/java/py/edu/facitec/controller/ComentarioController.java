package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;

@RestController

@RequestMapping("/api")
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping("/comentarios")

	public ResponseEntity<List<Comentario>> getComentarios() {

		List<Comentario> comentarios = new ArrayList<>();
		// BUSCAR TODOS
		comentarios = comentarioRepository.findAll();
		// t body y http status
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
	//Registrar
	@PostMapping("/comentario") // JSON --> JAVA
	public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario) {

		comentarioRepository.save(comentario);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);

	}
	@GetMapping("/comentario/{id}")
	public ResponseEntity<Comentario>
	                   
		getOneComentario(@PathVariable Long codigo){
    	
    	Optional<Comentario> comentario = comentarioRepository.findById(codigo);
   
    	if(comentario.isPresent()) {
    		return new ResponseEntity<Comentario>(comentario.get(), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
	}
	@DeleteMapping("/comentario/{codigo}")
    //Recibir por parametros el valor
	public ResponseEntity<Comentario>eliminarOneComentario(@PathVariable Long codigo) {

    	Optional<Comentario> comentario = comentarioRepository.findById(codigo);
 
    	          //Comparar si se encontro
    	if(comentario.isPresent()) {
    		comentarioRepository.deleteById(codigo);
    		
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
}
	
}
