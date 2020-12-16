package com.photoapp.api.users.data;

import java.io.Serializable;
import java.persistence.Entity;
import java.persistence.Table;
import java.persistence.Id;
import java.persistence.GeneratedValue;

@UserEntity
@Table(name="users")
public class UserEntity implements Serializable {
    
    private static final long serialVersionUID = -234513424523435678L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;
    
    @Column(nullable=false, length=50)
    private String lastName;
    
    @Column(nullable=false, length=120, unique=true)
    private String email;
    
    @Column(nullable=false, unique=true)
    private String userId;

    @Column(nullable=false, unique=true)
    private String encryptedPassword;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getUserId() {
		return this.email;
	}

	public void setUserId(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}