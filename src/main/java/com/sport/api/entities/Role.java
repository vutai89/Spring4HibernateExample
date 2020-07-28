package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseSystem {
	@Column
	@Size(max = 50)
	private String role_name;

	@Column
	@Size(max = 700)
	private String role_desc;

	@Column
	@Size(max = 1)
	private String status;

	@Column
	@Size(max = 50)
	private String role_id;

}
