package sbz.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserInfo {
	
	
	@Id
	@Column(name="id")
	private String id;
	@Column(name="adress")
	private String adress;
	@Column(name="bonus_points")
	private int bonus_points;

	//private KategorijaKupca kategorija;
	@ElementCollection
	@Column(name="shopng_history")
	private List<String> shoppingHistory = new ArrayList<String>();

	
	

	public UserInfo() {
		super();
	}


	public UserInfo(String id, String adress, int bonus_points) {
		super();
		this.id = id;
		this.adress = adress;
		this.bonus_points = bonus_points;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public int getBonus_points() {
		return bonus_points;
	}


	public void setBonus_points(int bonus_points) {
		this.bonus_points = bonus_points;
	}


	public List<String> getShoppingHistory() {
		return shoppingHistory;
	}


	public void setShoppingHistory(List<String> shoppingHistory) {
		this.shoppingHistory = shoppingHistory;
	}

	
	
}
