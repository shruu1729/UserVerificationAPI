package com.nagarro.training.userVerificationApi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
/**
 * @author shreyarathour
 * Represents the User entity with details like name, gender, nationality, etc.
 */

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	private String gender;
	
	private String nationality;
	
	@Column(name="verification_status")
	private String verificationStatus;
	
	private String dob;
	
	

	private int age;
	
	
	

	@Column(name="date_created")
	private String createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_modified")
	private Date modifiedDate;
	 /**
     * Constructor to initialize a User object with basic information.
     *
     * @param gender       Gender of the user
     * @param nationality  Nationality of the user
     * @param name         Name of the user
     * @param age          Age of the user
     * @param dob          Date of birth of the user
     */
	public User(String gender, String nationality, String name, int age, String dob) {
		
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
		this.age = age;
		this.verificationStatus = "TO_BE_VERIFIED";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date currentDate = new Date();
		this.createdDate = dateFormat.format(currentDate);
		
		this.dob = dob;
	}
	/**
     * Default constructor required by JPA.
     */
	public User() {
		// TODO Auto-generated constructor stub
	}
	/**
     * Get the user's ID.
     *
     * @return User ID
     */
	public Long getId() {
		return id;
	}
	/**
     * Set the user's ID.
     *
     * @param id User ID
     */
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/**
     * Override toString method to provide a string representation of the User object.
     *
     * @return String representation of the User
     */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", nationality=" + nationality
				+ ", verificationStatus=" + verificationStatus + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", age"+ age+  "]";
	}

}
