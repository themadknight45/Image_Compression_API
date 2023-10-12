package com.pl_project.pl_project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name ="User_information")
@Entity
public class User_Information {
	@Id
    private String username;
    public User_Information() {
		super();
	}
	@Column
    private String inputfileUrl;
    @Column
    private String compressesfileUrl;
    
    public User_Information(String username, String inputfileUrl, String compressesfileUrl) {
		super();
		this.username = username;
		this.inputfileUrl = inputfileUrl;
		this.compressesfileUrl = compressesfileUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInputfileUrl() {
		return inputfileUrl;
	}
	public void setInputfileUrl(String inputfileUrl) {
		this.inputfileUrl = inputfileUrl;
	}
	public String getCompressesfileUrl() {
		return compressesfileUrl;
	}
	public void setCompressesfileUrl(String compressesfileUrl) {
		this.compressesfileUrl = compressesfileUrl;
	}
	@Override
	public String toString() {
		return "User_Information [username=" + username + ", inputfileUrl=" + inputfileUrl + ", compressesfileUrl="
				+ compressesfileUrl + "]";
	}
}
