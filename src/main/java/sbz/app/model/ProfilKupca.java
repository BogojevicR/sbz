package sbz.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class ProfilKupca {
	
	
	
	
	@Id
	private String sifra;
	@Column(name="adresa")
	private String adresa;
	@Column(name="nagradni_bodovi")
	private int nagradni_bodovi;
	@ManyToOne
	private KategorijaKupca kategorija;
	//TODO 00: MOZDA TREBA OVDE TARGET ENTITY I OSTALI PARAMETRI KOD SVIH ANOTACIJA
	@Column(name="istorija_kupovina")
	@OneToMany
	private List<Racun> istorija_kupovina = new ArrayList<Racun>();

	
	

	public ProfilKupca() {
		super();
	}

	
	

	public ProfilKupca(String sifra, String adresa, int nagradni_bodovi, KategorijaKupca kategorija,
			List<Racun> istorija_kupovina) {
		super();
		this.sifra = sifra;
		this.adresa = adresa;
		this.nagradni_bodovi = nagradni_bodovi;
		this.kategorija = kategorija;
	}




	public String getSifra() {
		return sifra;
	}




	public void setSifra(String sifra) {
		this.sifra = sifra;
	}




	public String getAdresa() {
		return adresa;
	}




	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}




	public int getNagradni_bodovi() {
		return nagradni_bodovi;
	}




	public void setNagradni_bodovi(int nagradni_bodovi) {
		this.nagradni_bodovi = nagradni_bodovi;
	}




	public KategorijaKupca getKategorija() {
		return kategorija;
	}




	public void setKategorija(KategorijaKupca kategorija) {
		this.kategorija = kategorija;
	}




	public List<Racun> getIstorija_kupovina() {
		return istorija_kupovina;
	}




	public void setIstorija_kupovina(List<Racun> istorija_kupovina) {
		this.istorija_kupovina = istorija_kupovina;
	}




	@Override
	public String toString() {
		return "ProfilKupca [sifra=" + sifra + ", adresa=" + adresa + ", nagradni_bodovi=" + nagradni_bodovi
				+ ", kategorija=" + kategorija + ", istorija_kupovina=" + istorija_kupovina + "]";
	}


	
	



	
	
	
}
