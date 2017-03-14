package br.com.stefanini.progress.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_id_user")
	private int idUser;

	@Column(name = "fd_cpf")
	@CPF(message = "*Por favor forneça um CPF válido")
	@NotEmpty(message = "*Por favor forneça seu CPF")
	private String cpf;

	@Column(name = "fd_name")
	@NotEmpty(message = "*Por favor forneça seu nome")
	private String name;

	@Column(name = "fd_username")
	@NotEmpty(message = "*Por favor forneça seu nome de usuário")
	private String username;

	@Column(name = "fd_password")
	@Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
	@NotEmpty(message = "*Forneça uma senha")
	@Transient
	private String password;
	
	private String confirmPassword;

	@Column(name = "fd_active")
	private Boolean active;

	@Column(name = "fd_email")
	@Email(message = "*Por favor forneça um email válido")
	@NotEmpty(message = "*Forneça um e-mail")
	private String email;

//	@ManyToOne
//	@JoinColumn(name = "cd_id_profile", foreignKey = @ForeignKey(name = "fk_constraint_profile"))
//	private Profile idProfile;
	
//	private List<String>profile;
//
//	public List<String> getProfile() {
//		return profile;
//	}
//
//	public void setProfile(List<String> profile) {
//		this.profile = profile;
//	}

//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
//	@JoinTable(name = "tb_assoc_user_project", joinColumns = @JoinColumn(name = "cd_id_user"), inverseJoinColumns = @JoinColumn(name = "cd_id_project"))
//	private Set<Project> project;

	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "cd_id_user"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		checkPassword();
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		checkPassword();
	}

	private void checkPassword() {
		if(this.password == null || this.confirmPassword == null){
			return;
		} else if (!this.password.equals(confirmPassword)){
			this.confirmPassword = null;
		}
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
