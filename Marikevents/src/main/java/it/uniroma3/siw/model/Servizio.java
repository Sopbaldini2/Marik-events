package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
//import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servizio {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@NotBlank(message = "{servizio.nome.notblank}")
	private String nome;
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "{servizio.descrizione.notblank}")
	private String descrizione;
	@NotNull
	@Min(10)
	private Float prezzo;
	@NotBlank(message = "{servizio.image.notblank}")
	private String image;
	@NotBlank(message = "{servizio.video.notblank}")
	private String video;
	
	
	@ManyToMany(mappedBy="servizi")
	private List<Evento> eventi;

	public List<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(List<Evento> eventi) {
		this.eventi = eventi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrizione, id, nome, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servizio other = (Servizio) obj;
		return Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(prezzo, other.prezzo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
}

 