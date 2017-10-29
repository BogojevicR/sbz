package sbz.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="User")
public class User implements Serializable{
	public enum Role {CUSTOMER, MANAGER, EMPLOYEE };
	@Id
	@Column(name="username")
	private String username;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="password")
	private String password;
	@Enumerated
	@Column(name="role")
	private Role role;
	@Column(name="registration_date")
	private Date registration_date;
	
	@OneToOne
	private UserInfo user_info;
	
	
	public User(){
		super();
	}
	
	
	public User(String username, String first_name, String last_name, String password, Role role,
			Date registration_date, UserInfo user_info) {
		super();
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.role = role;
		this.registration_date = registration_date;
		this.user_info = user_info;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Date getRegistration_date() {
		return registration_date;
	}


	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}


	public UserInfo getUser_info() {
		return user_info;
	}


	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
	}
	
	
	
	
	
}
