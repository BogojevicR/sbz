package sbz.app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Racun {

	
	public enum StanjeRacuna {
		PORUCENO, OTKAZANO, REALIZOVANO
	};

	@Id
	private String sifra;
	private Date datum;
	@OneToOne
	private Korisnik kupac;
	@Enumerated
	private StanjeRacuna stanje;
	private float originalnaCena;
	private float procenatUmanjenja;
	private float konacnaCena;
	private int brojPotrosenihBodova;
	private int brojOstvarenihBodova;
	
	//@ElementCollection
	@OneToMany
	private List<PopustNaRacun> listaPopusta = new ArrayList<PopustNaRacun>();
	
	//@ElementCollection
	@OneToMany
	private List<StavkaRacuna> listaStavki = new ArrayList<StavkaRacuna>();
	
	public Racun() {
		super();
	}

	public Racun(String sifra, Date datum, Korisnik kupac, StanjeRacuna stanje, float originalnaCena,
			float procenatUmanjenja, float konacnaCena, int brojPotrosenihBodova, int brojOstvarenihBodova,
			List<PopustNaRacun> listaPopusta, List<StavkaRacuna> listaStavki) {
		super();
		this.sifra = sifra;
		this.datum = datum;
		this.kupac = kupac;
		this.stanje = stanje;
		this.originalnaCena = originalnaCena;
		this.procenatUmanjenja = procenatUmanjenja;
		this.konacnaCena = konacnaCena;
		this.brojPotrosenihBodova = brojPotrosenihBodova;
		this.brojOstvarenihBodova = brojOstvarenihBodova;
		this.listaPopusta = listaPopusta;
		this.listaStavki = listaStavki;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public StanjeRacuna getStanje() {
		return stanje;
	}

	public void setStanje(StanjeRacuna stanje) {
		this.stanje = stanje;
	}

	public float getOriginalnaCena() {
		return originalnaCena;
	}

	public void setOriginalnaCena(float originalnaCena) {
		this.originalnaCena = originalnaCena;
	}

	public float getProcenatUmanjenja() {
		return procenatUmanjenja;
	}

	public void setProcenatUmanjenja(float procenatUmanjenja) {
		this.procenatUmanjenja = procenatUmanjenja;
	}

	public float getKonacnaCena() {
		return konacnaCena;
	}

	public void setKonacnaCena(float konacnaCena) {
		this.konacnaCena = konacnaCena;
	}

	public int getBrojPotrosenihBodova() {
		return brojPotrosenihBodova;
	}

	public void setBrojPotrosenihBodova(int brojPotrosenihBodova) {
		this.brojPotrosenihBodova = brojPotrosenihBodova;
	}

	public int getBrojOstvarenihBodova() {
		return brojOstvarenihBodova;
	}

	public void setBrojOstvarenihBodova(int brojOstvarenihBodova) {
		this.brojOstvarenihBodova = brojOstvarenihBodova;
	}

	public List<PopustNaRacun> getListaPopusta() {
		return listaPopusta;
	}

	public void setListaPopusta(List<PopustNaRacun> listaPopusta) {
		this.listaPopusta = listaPopusta;
	}

	public List<StavkaRacuna> getListaStavki() {
		return listaStavki;
	}

	public void setListaStavki(List<StavkaRacuna> listaStavki) {
		this.listaStavki = listaStavki;
	}

	@Override
	public String toString() {
		return "Racun [sifra=" + sifra + ", datum=" + datum + ", kupac=" + kupac + ", stanje=" + stanje
				+ ", originalnaCena=" + originalnaCena + ", procenatUmanjenja=" + procenatUmanjenja + ", konacnaCena="
				+ konacnaCena + ", brojPotrosenihBodova=" + brojPotrosenihBodova + ", brojOstvarenihBodova="
				+ brojOstvarenihBodova + ", listaPopusta=" + listaPopusta + ", listaStavki=" + listaStavki + "]";
	}

	public void podesiRacun(Racun racun) {
		racun.setSifra(UUID.randomUUID().toString().replaceAll("-", ""));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		racun.setDatum(date); 
		racun.setStanje(StanjeRacuna.PORUCENO);
		for(StavkaRacuna sr: racun.getListaStavki()){
			sr.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		//	sr.setRacun(racun);
			racun.originalnaCena+=racun.originalnaCena+sr.getCena();
		} 
		
	}
	
	
	//TODO 2: Popravi relacije u bazi
	
	
	
	
}
