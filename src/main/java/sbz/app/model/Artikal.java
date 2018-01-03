package sbz.app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Artikal {
	
public enum StatusZapisa {AKTIVAN, ARHIVIRAN}
	
	@Id
	private String sifra;
	private String naziv;
	@ManyToOne
	private KategorijaArtikla kategorija;
	private float cena;
	private int brojnoStanje;
	private Date datum_kreiranja;
	private boolean treba_zaliha;
	@Enumerated
	private StatusZapisa status_zapisa;
	private int minimalno_stanje;
	
	
	
	
	
	
	public Artikal() {
		super();
	}






	public Artikal(String sifra, String naziv, KategorijaArtikla kategorija, float cena, int brojnoStanje,
			Date datum_kreiranja, boolean treba_zaliha, StatusZapisa status_zapisa, int minimalno_stanje) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.kategorija = kategorija;
		this.cena = cena;
		this.brojnoStanje = brojnoStanje;
		this.datum_kreiranja = datum_kreiranja;
		this.treba_zaliha = treba_zaliha;
		this.status_zapisa = status_zapisa;
		this.minimalno_stanje = minimalno_stanje;
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






	public KategorijaArtikla getKategorija() {
		return kategorija;
	}






	public void setKategorija(KategorijaArtikla kategorija) {
		this.kategorija = kategorija;
	}






	public float getCena() {
		return cena;
	}






	public void setCena(float cena) {
		this.cena = cena;
	}






	public int getBrojnoStanje() {
		return brojnoStanje;
	}






	public void setBrojnoStanje(int brojnoStanje) {
		this.brojnoStanje = brojnoStanje;
	}






	public Date getDatum_kreiranja() {
		return datum_kreiranja;
	}






	public void setDatum_kreiranja(Date datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}






	public boolean isTreba_zaliha() {
		return treba_zaliha;
	}






	public void setTreba_zaliha(boolean treba_zaliha) {
		this.treba_zaliha = treba_zaliha;
	}






	public StatusZapisa getStatus_zapisa() {
		return status_zapisa;
	}






	public void setStatus_zapisa(StatusZapisa status_zapisa) {
		this.status_zapisa = status_zapisa;
	}






	public int getMinimalno_stanje() {
		return minimalno_stanje;
	}






	public void setMinimalno_stanje(int minimalno_stanje) {
		this.minimalno_stanje = minimalno_stanje;
	}


	public void podesi(Artikal art){		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//dateFormat.format(date);
		
		art.setDatum_kreiranja(date);
		art.setStatus_zapisa(StatusZapisa.AKTIVAN);
	}



	@Override
	public String toString() {
		return "Artikal [sifra=" + sifra + ", naziv=" + naziv + ", kategorija=" + kategorija + ", cena=" + cena
				+ ", brojnoStanje=" + brojnoStanje + ", datum_kreiranja=" + datum_kreiranja + ", treba_zaliha="
				+ treba_zaliha + ", status_zapisa=" + status_zapisa + ", minimalno_stanje=" + minimalno_stanje + "]";
	}
	
	
	
	
	
	

}
