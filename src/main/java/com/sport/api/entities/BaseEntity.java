package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGen;
	@Column
	@Size(max = 5)
	private String id;
	@Column
	@Size(max = 500)
	private String name;
//	@Column
//	@Size(max = 250)
//	private String location;
	@Column
	@Size(max = 100)
	private String type;
}
