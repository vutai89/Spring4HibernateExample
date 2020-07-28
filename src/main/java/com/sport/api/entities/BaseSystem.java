package com.sport.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseSystem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@Size(max = 50)
	@CreatedBy
	private String create_by;
	
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column
	private Date create_date = new Date();
	
	@Column
	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	private Date modified_date;
	
	@Column
	@Size(max = 50)
	@LastModifiedBy
	private String modified_by;	
}
