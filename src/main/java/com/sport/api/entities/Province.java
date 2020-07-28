package com.sport.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "province")
@ToString
@Getter
@Setter
public class Province extends BaseEntity{	
}
