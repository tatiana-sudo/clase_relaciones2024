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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;

//Aplicar la arquitectura rest
//Representational State Transfer
//Solicitudes sin alm. de estado
@RestController

//api afecta a todos los metodos de la clase
@RequestMapping("/api")
public class SuscritoController {

	// Autowired de Suscrito Repository
	@Autowired
	private SuscritoRepository suscritoRepository;

	// api
	// nombre de la lista
	@GetMapping("/suscritos")

	public ResponseEntity<List<Suscrito>> getSuscritos() {

		List<Suscrito> suscritos = new ArrayList<>();

		// BUSCAR TODOS
		suscritos = suscritoRepository.findAll();
		// t body y http status
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}

	@PostMapping("/suscrito") // JSON --> JAVA
	public ResponseEntity<Suscrito> guardarSuscrito(@RequestBody Suscrito suscrito) {

		suscritoRepository.save(suscrito);

		// Devolver el objeto nuevo creado
		// id nuevo
		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);

	}

	// api/suscrit/{id}
	// buscar un suscrito por codigo
	@GetMapping("/suscrito/{codigo}")
	public ResponseEntity<Suscrito>
	                    //Recibir por parametros el valor
		getOneSuscrito(@PathVariable Long codigo){
    	
    	Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);
    	
    	          //Comparar si se encontro
    	if(suscrito.isPresent()) {
    		return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
	}
    		//api/suscrito/codigo
    	//eliminar un suscrito por codigo Delete
    	 @DeleteMapping("/suscrito/{codigo}")
    	    //Recibir por parametros el valor
    		public ResponseEntity<Suscrito>eliminarOneSuscrito(@PathVariable Long codigo) {
    
    	    	Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);
    	 
    	    	          //Comparar si se encontro
    	    	if(suscrito.isPresent()) {
    	    		suscritoRepository.deleteById(codigo);
    	    		
    	    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	    	}else {
    	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	    	}
	}

}
