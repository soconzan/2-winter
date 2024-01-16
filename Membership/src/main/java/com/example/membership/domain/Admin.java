package com.example.membership.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admins")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Admin {
	
	@Id
	private String adminId;
	
	private String pw;
}
