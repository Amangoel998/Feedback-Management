package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminDTO {

	@Id
	@Column(name = "admin_id")
	private String adminId;

	@Column(name = "admin_name")
	private String adminName;

	@Column(name = "admin_email")
	private String adminEmail;

	public String getAdminId() {
		return adminId;
	}

	public AdminDTO() {
		super();
	}

	public AdminDTO(String adminId, String adminName, String adminEmail) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	
	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", adminName=" + adminName + "]";
	}
}
