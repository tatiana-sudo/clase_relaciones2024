package py.edu.facitec.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario extends General {
	private String texto;
	private Integer estrellas;
	
	@JsonBackReference(value="relacion1")
	@ManyToOne
	private Post post;

	@ManyToOne
	private Suscrito suscrito;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Integer estrellas) {
		this.estrellas = estrellas;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Suscrito getSuscrito() {
		return suscrito;
	}

	public void setSuscrito(Suscrito suscrito) {
		this.suscrito = suscrito;
	}

	@Override
	public String toString() {
		return "Comentario [texto=" + texto + ", estrellas=" + estrellas + ", post=" + post + ", suscrito=" + suscrito
				+ "]";
	}

	
	
	
}
