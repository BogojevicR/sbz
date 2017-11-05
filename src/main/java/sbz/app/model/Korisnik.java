package sbz.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
public class Korisnik implements Serializable{
	public enum Role {KUPAC, MENADZER, PRODAVAC };
	@Id
	@Column(name="username")
	private String username;
	@Column(name="ime")
	private String ime;
	@Column(name="prezime")
	private String prezime;
	@Column(name="password")
	private String password;
	@Enumerated
	@Column(name="uloga")
	private Role uloga;
	@Column(name="datum_registrovanja")
	private Date datum_registrovanja;
	
	@OneToOne
	private ProfilKupca profil_kupca;
	
	
	public Korisnik(){
		super();
	}
	
	
	public Korisnik(String username, String first_name, String last_name, String password, Role role,
			Date registration_date, ProfilKupca user_info) {
		super();
		this.username = username;
		this.ime = first_name;
		this.prezime = last_name;
		this.password = password;
		this.uloga = role;
		this.datum_registrovanja = registration_date;
		this.profil_kupca = user_info;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirst_name() {
		return ime;
	}


	public void setFirst_name(String first_name) {
		this.ime = first_name;
	}


	public String getLast_name() {
		return prezime;
	}


	public void setLast_name(String last_name) {
		this.prezime = last_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return uloga;
	}


	public void setRole(Role role) {
		this.uloga = role;
	}


	public Date getRegistration_date() {
		return datum_registrovanja;
	}


	public void setRegistration_date(Date registration_date) {
		this.datum_registrovanja = registration_date;
	}


	public ProfilKupca getUser_info() {
		return profil_kupca;
	}


	public void setUser_info(ProfilKupca user_info) {
		this.profil_kupca = user_info;
	}
	
	
	
	
	
}
