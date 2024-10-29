package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Post;

import py.edu.facitec.repository.PostRepository;

@RestController

@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/posts")

	public ResponseEntity<List<Post>> getPosts() {

		List<Post> posts = new ArrayList<>();
		// BUSCAR TODOS
		posts = postRepository.findAll();
		// t body y http status
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	//Registrar
	@PostMapping("/post") // JSON --> JAVA
	public ResponseEntity<Post> guardarPost(@RequestBody Post post) {

		postRepository.save(post);
		return new ResponseEntity<Post>(post, HttpStatus.OK);

	}
	@GetMapping("/post/{id}")
	public ResponseEntity<Post>
	                   
		getOnePost(@PathVariable Long codigo){
    	
    	Optional<Post> post = postRepository.findById(codigo);
   
    	if(post.isPresent()) {
    		return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
	}
	
}
