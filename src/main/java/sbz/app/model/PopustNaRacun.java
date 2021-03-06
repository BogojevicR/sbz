package sbz.app.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PopustNaRacun {
	
	public enum VrstaPopusta {OSNOVNI, DODATNI}
	@Id
	private String sifra;
	@ManyToOne
	private Racun racun;
	private float procenatUmanjenja;
	@Enumerated
	private VrstaPopusta vrstaPopusta;
	
	
	public PopustNaRacun() {
		super();
	}


	public PopustNaRacun(Racun racun, float procenatUmanjenja, VrstaPopusta vrstaPopusta) {
		super();
		this.sifra = UUID.randomUUID().toString().replaceAll("-", "");
		this.racun = racun;
		this.procenatUmanjenja = procenatUmanjenja;
		this.vrstaPopusta = vrstaPopusta;
	}


	public String getSifra() {
		return sifra;
	}


	public void setSifra(String sifra) {
		this.sifra = sifra;
	}


	public Racun getRacun() {
		return racun;
	}


	public void setRacun(Racun racun) {
		this.racun = racun;
	}


	public float getProcenatUmanjenja() {
		return procenatUmanjenja;
	}


	public void setProcenatUmanjenja(float procenatUmanjenja) {
		this.procenatUmanjenja = procenatUmanjenja;
	}


	public VrstaPopusta getVrstaPopusta() {
		return vrstaPopusta;
	}


	public void setVrstaPopusta(VrstaPopusta vrstaPopusta) {
		this.vrstaPopusta = vrstaPopusta;
	}


	@Override
	public String toString() {
		return "PopustNaRacun [sifra=" + sifra + ", racun=" + racun + ", procenatUmanjenja=" + procenatUmanjenja
				+ ", vrstaPopusta=" + vrstaPopusta + "]";
	}
	
	
}
