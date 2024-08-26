package it.uniroma3.siw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Credentials {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String username;
	private String password;
	private String ruolo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Amministratore marik;
	
	public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setRole(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public Amministratore getMarik() {
		return marik;
	}

	public void setMarik(Amministratore marik) {
		this.marik = marik;
	}

}

