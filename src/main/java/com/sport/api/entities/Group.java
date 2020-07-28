package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "group")
@Getter
@Setter
public class Group extends BaseSystem {
	@Column
	@Size(max = 50)
	private String group_name;

	@Column
	@Size(max = 700)
	private String group_desc;

	@Column
	@Size(max = 1)
	private String locked;

	@Column
	@Size(max = 50)
	private String group_id;
}
