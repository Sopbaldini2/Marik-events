package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Evento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
	@NotBlank(message = "{evento.nome.notblank}")
	private String nome;
	@NotNull
	@Min(10)
	private Float prezzo;
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "{evento.descrizione.notblank}")
	private String descrizione;
	@NotBlank(message = "{evento.tipologia.notblank}")
	private String tipologia;
	@NotBlank(message = "{evento.image.notblank}")
	private String image;
	@NotNull(message = "{evento.data.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@OneToMany(mappedBy="evento")
	private List<Recensione> recensioni;

	@ManyToMany
	private List<Servizio> servizi;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(data, descrizione, id, nome, prezzo, recensioni, servizi,
				tipologia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(data, other.data)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(id, other.id)
			    && Objects.equals(nome, other.nome)
				&& Objects.equals(prezzo, other.prezzo) && Objects.equals(recensioni, other.recensioni)
				&& Objects.equals(servizi, other.servizi) && Objects.equals(tipologia, other.tipologia);
	}
	
	public List<Servizio> getServizi() {
		return servizi;
	}
	public void setServizi(List<Servizio> servizi) {
		this.servizi = servizi;
	}
	public List<Recensione> getRecensioni() {
		return recensioni;
	}
	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
