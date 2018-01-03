package sbz.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class PragPotrosnje {

	
	@Id
	private String sifra;
	
	private int donja_granica;
	private int gornja_granica;
	private double procenat;
	
	
	public PragPotrosnje() {
		super();
	}





	public PragPotrosnje(String id, int donja_granica, int gornja_granica, double procenat) {
		super();
		this.sifra = id;
		this.donja_granica = donja_granica;
		this.gornja_granica = gornja_granica;
		this.procenat = procenat;
	}





	public String getId() {
		return sifra;
	}





	public void setId(String id) {
		this.sifra = id;
	}





	public int getDonja_granica() {
		return donja_granica;
	}





	public void setDonja_granica(int donja_granica) {
		this.donja_granica = donja_granica;
	}





	public int getGornja_granica() {
		return gornja_granica;
	}





	public void setGornja_granica(int gornja_granica) {
		this.gornja_granica = gornja_granica;
	}





	public double getProcenat() {
		return procenat;
	}





	public void setProcenat(double procenat) {
		this.procenat = procenat;
	}





	@Override
	public String toString() {
		return "PragPotrosnje [sifra=" + sifra + ", donja_granica=" + donja_granica + ", gornja_granica="
				+ gornja_granica + ", procenat=" + procenat + "]";
	}
	
	
	
	
	//TODO 1: Implementiraj funkciju dodele za pragove
	
	
	
	
}
