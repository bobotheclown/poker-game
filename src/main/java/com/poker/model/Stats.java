package com.poker.model;

import javax.persistence.*;


@Entity
@Table(name = "stats")
public class Stats {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	public Stats() {
		
	}
	
	public Stats(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	
}
