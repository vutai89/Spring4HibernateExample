package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ward")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ward extends BaseEntity {
	@Column
	private String district_id;
}
