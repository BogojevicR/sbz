package sbz.app.model;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import requests.Requests;
import sbz.app.repository.RacunRepository;


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
	
	public Korisnik() {
		super();
	}

	
	

	public Korisnik(String username, String ime, String prezime, String password, Role uloga, Date datum_registrovanja,
			ProfilKupca profil_kupca) {
		super();
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.uloga = uloga;
		this.datum_registrovanja = datum_registrovanja;
		this.profil_kupca = profil_kupca;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getIme() {
		return ime;
	}




	public void setIme(String ime) {
		this.ime = ime;
	}




	public String getPrezime() {
		return prezime;
	}




	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Role getUloga() {
		return uloga;
	}




	public void setUloga(Role uloga) {
		this.uloga = uloga;
	}




	public Date getDatum_registrovanja() {
		return datum_registrovanja;
	}




	public void setDatum_registrovanja(Date datum_registrovanja) {
		this.datum_registrovanja = datum_registrovanja;
	}




	public ProfilKupca getProfil_kupca() {
		return profil_kupca;
	}




	public void setProfil_kupca(ProfilKupca profil_kupca) {
		this.profil_kupca = profil_kupca;
	}
	
	public void podesiprofil(Korisnik korisnik){
		korisnik.getProfil_kupca().setSifra(UUID.randomUUID().toString().replaceAll("-", ""));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//dateFormat.format(date);
		
		korisnik.setDatum_registrovanja(date);
		if(korisnik.getUloga()==Korisnik.Role.KUPAC){
			korisnik.getProfil_kupca().setIstorija_kupovina(new ArrayList<String>());
		}else{
			korisnik.setProfil_kupca(null);
		}
	}
	


	@Override
	public String toString() {
		return "Korisnik [username=" + username + ", ime=" + ime + ", prezime=" + prezime + ", password=" + password
				+ ", uloga=" + uloga + ", datum_registrovanja=" + datum_registrovanja + ", profil_kupca=" + profil_kupca
				+ "]";
	}
	
}
