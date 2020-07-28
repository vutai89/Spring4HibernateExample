package com.sport.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "football_club")
@Getter
@Setter
public class FootbalClub extends BaseSystem {
	@Column
	private String club_id;

	@Column
	private String club_name;
	@Column
	@Size(max = 50)
	private String team_capital;
}
