package com.sport.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User extends BaseSystem {

	@Column
	@Size(max = 50)
	private String user_id;

	@Column
	@Size(max = 250)
	private String full_name;

	@Column
	@Size(max = 1)
	private String gender;

	@Column
	private Date birth_day;

	@Column
	@Size(max = 100)
	private String email;

	@Column
	@Size(max = 15)
	private String phone;

	@Column
	@Size(max = 500)
	private String address;

	@Column
	@Size(max = 500)
	private String password;

	@Column
	@Size(max = 1)
	private String active;

	@Column
	@Size(max = 1)
	private String locked;

	@Column
	@Size(max = 1000)
	private String reason_locked;

	@Column
	@Size(max = 1)
	private String is_boss;

	@Column
	@Size(max = 1)
	private String is_manager;

	@Column
	@Size(max = 20)
	private String club_id;
}
