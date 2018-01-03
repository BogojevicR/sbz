package sbz.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class KategorijaArtikla {

	@Id
	private String sifra;
	private String naziv;
	@ManyToOne
	private KategorijaArtikla nadkategorija;
	private float maksimalni_popust;
	
	
	
	
	
	public KategorijaArtikla() {
		super();
	}




	public KategorijaArtikla(String sifra, String naziv, KategorijaArtikla nadkategorija, float maksimalni_popust) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.nadkategorija = nadkategorija;
		this.maksimalni_popust = maksimalni_popust;
	}




	public String getSifra() {
		return sifra;
	}




	public void setSifra(String sifra) {
		this.sifra = sifra;
	}




	public String getNaziv() {
		return naziv;
	}




	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}




	public KategorijaArtikla getNadkategorija() {
		return nadkategorija;
	}




	public void setNadkategorija(KategorijaArtikla nadkategorija) {
		this.nadkategorija = nadkategorija;
	}




	public float getMaksimalni_popust() {
		return maksimalni_popust;
	}




	public void setMaksimalni_popust(float maksimalni_popust) {
		this.maksimalni_popust = maksimalni_popust;
	}




	@Override
	public String toString() {
		return "KategorijaArtikla [sifra=" + sifra + ", naziv=" + naziv + ", nadkategorija=" + nadkategorija
				+ ", maksimalni_popust=" + maksimalni_popust + "]";
	}
	
	
	
	
	
	
}
