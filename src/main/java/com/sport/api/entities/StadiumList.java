package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "stadium_list")
@Getter
@Setter
public class StadiumList extends BaseSystem{
	@Column
	@Size(max = 50)
	private String stadium_id;
	
	@Column
	@Size(max = 500)
	private String stadium_name;
	
	@Column
	@Size(max = 700)
	private String stadium_address;
	
	@Column
	@Size(max = 1)
	private String active;
	
	@Column
	@Size(max = 1)
	private String is_full;
	
	@Column
	@Size(max = 50)
	private String stadium_boss;
	
	@Column
	@Size(max = 500)
	private String stadium_manager;
	
	@Column
	private int stadium_total;
}
