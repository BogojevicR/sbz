package sbz.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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


	public void setKonacnaCenaStavke(float konacnaCenaStavke) {
		this.konacnaCenaStavke = konacnaCenaStavke;
	}


	public List<PopustNaStavku> getListaPopusta() {
		return listaPopusta;
	}


	public void setListaPopusta(List<PopustNaStavku> listaPopusta) {
		this.listaPopusta = listaPopusta;
	}


	@Override
	public String toString() {
		return "StavkaRacuna [id=" + id + ", racun=" + racun + ", redniBroj=" + redniBroj + ", artikal=" + artikal
				+ ", cena=" + cena + ", kolicina=" + kolicina + ", originalnaUkupnaCenaStavke="
				+ originalnaUkupnaCenaStavke + ", procenatUmanjenja=" + procenatUmanjenja + ", konacnaCenaStavke="
				+ konacnaCenaStavke + ", listaPopusta=" + listaPopusta + "]";
	}
	
	
	
	
	

}
