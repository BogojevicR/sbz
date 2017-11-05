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


	public ProfilKupca(String id, String adress,KategorijaKupca kp, int bonus_points) {
		super();
		this.sifra = id;
		this.adresa = adress;
		this.kategorija=kp;
		this.nagradni_bodovi = bonus_points;
	}


	public String getId() {
		return sifra;
	}


	public void setId(String id) {
		this.sifra = id;
	}


	public String getAdress() {
		return adresa;
	}


	public void setAdress(String adress) {
		this.adresa = adress;
	}


	public int getBonus_points() {
		return nagradni_bodovi;
	}


	public void setBonus_points(int bonus_points) {
		this.nagradni_bodovi = bonus_points;
	}


	public List<Racun> getShoppingHistory() {
		return istorija_kupovina;
	}


	public void setShoppingHistory(List<Racun> shoppingHistory) {
		this.istorija_kupovina = shoppingHistory;
	}


	public KategorijaKupca getKategorija() {
		return kategorija;
	}


	public void setKategorija(KategorijaKupca kategorija) {
		this.kategorija = kategorija;
	}

	
	
	
}
