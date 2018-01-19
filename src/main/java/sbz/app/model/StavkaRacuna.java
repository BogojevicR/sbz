package sbz.app.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.map.ObjectMapper;

import requests.Requests;
import sbz.app.model.PopustNaRacun.VrstaPopusta;

@Entity

public class StavkaRacuna {
	
	
	@Id
	private String id;
	@ManyToOne
	private Racun racun;
	//@Indexed(unique = true)
	private int redniBroj;
	@ManyToOne
	private Artikal artikal;
	private float cena;
	private int kolicina;
	private float originalnaUkupnaCenaStavke;
	private float procenatUmanjenja;
	private float konacnaCenaStavke;
	
	
	//@ElementCollection
	@OneToMany
	private List<PopustNaStavku> listaPopusta = new ArrayList<PopustNaStavku>();
	
	
	public StavkaRacuna() {
		super();
	}


	public StavkaRacuna(String id, Racun racun, int redniBroj, Artikal artikal, float cena, int kolicina,
			float originalnaUkupnaCenaStavke, float procenatUmanjenja, float konacnaCenaStavke,
			List<PopustNaStavku> listaPopusta) {
		super();
		this.id = id;
		this.racun = racun;
		this.redniBroj = redniBroj;
		this.artikal = artikal;
		this.cena = cena;
		this.kolicina = kolicina;
		this.originalnaUkupnaCenaStavke = originalnaUkupnaCenaStavke;
		this.procenatUmanjenja = procenatUmanjenja;
		this.konacnaCenaStavke = konacnaCenaStavke;
		this.listaPopusta = listaPopusta;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Racun getRacun() {
		return racun;
	}


	public void setRacun(Racun racun) {
		this.racun = racun;
	}


	public int getRedniBroj() {
		return redniBroj;
	}


	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}


	public Artikal getArtikal() {
		return artikal;
	}


	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}


	public float getCena() {
		return cena;
	}


	public void setCena(float cena) {
		this.cena = cena;
	}


	public int getKolicina() {
		return kolicina;
	}


	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}


	public float getOriginalnaUkupnaCenaStavke() {
		return originalnaUkupnaCenaStavke;
	}


	public void setOriginalnaUkupnaCenaStavke(float originalnaUkupnaCenaStavke) {
		this.originalnaUkupnaCenaStavke = originalnaUkupnaCenaStavke;
	}


	public float getProcenatUmanjenja() {
		return procenatUmanjenja;
	}


	public void setProcenatUmanjenja(float procenatUmanjenja) {
		this.procenatUmanjenja = procenatUmanjenja;
	}


	public float getKonacnaCenaStavke() {
		return konacnaCenaStavke;
	}
	
	public float najboljiPopust() {
		float najbolji=0;

		for(PopustNaStavku popust : this.getListaPopusta()){
		
			if(popust.getVrstaPopusta()==PopustNaStavku.VrstaPopusta.OSNOVNI && popust.getProcenatUmanjenja()>najbolji){
				najbolji=popust.getProcenatUmanjenja();
			}
		}
		return najbolji;
	}
	
	public boolean proveriPopust(){
		System.out.println(this.getArtikal().getNaziv()+" Kat: "+ this.getArtikal().getKategorija().getNaziv());
		System.out.println(this.getArtikal().getKategorija().getMaksimalni_popust()>this.procenatUmanjenja);
		System.out.println(this.getArtikal().getKategorija().getMaksimalni_popust()+"   " +this.procenatUmanjenja);
		if(this.getArtikal().getKategorija().getMaksimalni_popust()>this.procenatUmanjenja){
			
			return true;
		}
		return false;
	}
	
	public float getMaxPopust(){
		return this.getArtikal().getKategorija().getMaksimalni_popust();
	}


	public void setKonacnaCenaStavke(float konacnaCenaStavke) {
		this.konacnaCenaStavke = konacnaCenaStavke;
	}


	public List<PopustNaStavku> getListaPopusta() {
		return listaPopusta;
	}


	public void setListaPopusta(List<PopustNaStavku> listaPopusta) {
		this.listaPopusta = listaPopusta;
	}
	
	public boolean kupovina15(Korisnik k) throws  IOException{
		String historylista = new Requests().makeGetRequest("http://localhost:8080/user/getHistory/"+k.getUsername());
		Racun[] pojos = new ObjectMapper().readValue(historylista, Racun[].class);
		List<Racun> history = Arrays.asList(pojos);
		
		
		Date danas = new Date();
		
		for(Racun racun : history){
			for(StavkaRacuna sr : racun.getListaStavki()){
				if(sr.getArtikal().getSifra().equals(artikal.getSifra())){
					if((System.currentTimeMillis() - racun.getDatum().getTime()) / (1000d) < 15){
						return true;
					}
					
				}
			}
		}
		
		return false;
		
	}


	@Override
	public String toString() {
		return "StavkaRacuna [id=" + id + ", racun=" + racun + ", redniBroj=" + redniBroj + ", artikal=" + artikal
				+ ", cena=" + cena + ", kolicina=" + kolicina + ", originalnaUkupnaCenaStavke="
				+ originalnaUkupnaCenaStavke + ", procenatUmanjenja=" + procenatUmanjenja + ", konacnaCenaStavke="
				+ konacnaCenaStavke + ", listaPopusta=" + listaPopusta + "]";
	}
	
	
	
	
	

}
