package sbz.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class KategorijaKupca {

	
	@Id
	@Column(name="sifra")
	private String sifra;
	@Column(name="ime_kategorije")
	private String ime_kategorije;
	
	@OneToMany
	@Column(name="pragovi_potrosnje")
	private List<PragPotrosnje> pragoviPotrosnje = new ArrayList<PragPotrosnje>();

	
	
	
	public KategorijaKupca() {
		super();
	}




	public KategorijaKupca(String id, String ime_kategorije, List<PragPotrosnje> pragoviPotrosnje) {
		super();
		this.sifra = id;
		this.ime_kategorije = ime_kategorije;
		this.pragoviPotrosnje = pragoviPotrosnje;
	}




	public String getId() {
		return sifra;
	}




	public void setId(String id) {
		this.sifra = id;
	}




	public String getIme_kategorije() {
		return ime_kategorije;
	}




	public void setIme_kategorije(String ime_kategorije) {
		this.ime_kategorije = ime_kategorije;
	}




	public List<PragPotrosnje> getPragoviPotrosnje() {
		return pragoviPotrosnje;
	}




	public void setPragoviPotrosnje(List<PragPotrosnje> pragoviPotrosnje) {
		this.pragoviPotrosnje = pragoviPotrosnje;
	}
	
	
	
	
	
	
	
}
