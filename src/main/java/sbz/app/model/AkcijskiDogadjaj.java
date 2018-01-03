package sbz.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class AkcijskiDogadjaj {
	
	@Id
	private String sifra;
	private String naziv;
	private Date pocetak;
	private Date zavrsetak;
	private float popust;
	@ManyToMany
	//TODO 01: MOZDA TREBA @ElementCollection kod svih lista
	private List<KategorijaArtikla> listaKategorija;
	
	
	public AkcijskiDogadjaj() {
		super();
	}





	public AkcijskiDogadjaj(String sifra, String naziv, Date pocetak, Date zavrsetak, float popust,
			List<KategorijaArtikla> listaKategorija) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.zavrsetak = zavrsetak;
		this.popust = popust;
		this.listaKategorija = listaKategorija;
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





	public Date getPocetak() {
		return pocetak;
	}





	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}





	public Date getZavrsetak() {
		return zavrsetak;
	}





	public void setZavrsetak(Date zavrsetak) {
		this.zavrsetak = zavrsetak;
	}





	public float getPopust() {
		return popust;
	}





	public void setPopust(float popust) {
		this.popust = popust;
	}





	public List<KategorijaArtikla> getListaKategorija() {
		return listaKategorija;
	}





	public void setListaKategorija(List<KategorijaArtikla> listaKategorija) {
		this.listaKategorija = listaKategorija;
	}





	@Override
	public String toString() {
		return "AkcijskiDogadjaj [sifra=" + sifra + ", naziv=" + naziv + ", pocetak=" + pocetak + ", zavrsetak="
				+ zavrsetak + ", popust=" + popust + ", listaKategorija=" + listaKategorija + "]";
	}
	
	
	
	

}
