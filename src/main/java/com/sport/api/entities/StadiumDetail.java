package com.sport.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stadium_detail")
@Getter
@Setter
public class StadiumDetail extends BaseSystem{
	@Column
	@Size(max = 50)
	private String stadium_id;
	
	@Column
	private int stadium_number;
	@Column
	private String stadium_booked;
	@Column
	private Date day;
	@Column
	private String time_start;
	@Column
	private String time_end;
	
	
	@Column
	@Size(max = 50)
	private String club_id;
	
}
