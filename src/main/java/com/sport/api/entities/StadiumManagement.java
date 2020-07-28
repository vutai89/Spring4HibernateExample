package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stadium_management")
@Getter
@Setter
public class StadiumManagement extends BaseSystem{
	@Column
	@Size(max = 50)
	private String stadium_id;
	
	@Column
	private int stadium_number;
	
	@Column
	@Size(max = 1)
	private int stadium_active;
	
	@Column
	@Size(max = 1)
	private int stadium_full;
	
}
