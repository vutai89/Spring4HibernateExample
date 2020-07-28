package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "District")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class District extends BaseEntity {
	
	@Column
	private String province_id;

}
