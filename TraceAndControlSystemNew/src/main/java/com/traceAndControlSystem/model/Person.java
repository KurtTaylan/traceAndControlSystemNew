package com.traceAndControlSystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * Person generated by hbm2java
 */
@Entity
@Table(name = "person", catalog = "demotest")
public class Person implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String position;
	private String workDate;
	private Tribe tribe;
	
	
	public Person() {
	}

	public Person(String name, String position, String workDate) {
		this.name = name;
		this.position = position;
		this.workDate = workDate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "position", nullable = false, length = 45)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@Column(name = "work_date", nullable = false, length = 45)
	public String getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="tribe_person",
	joinColumns={@JoinColumn(name="person_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="tribe_id", referencedColumnName="id")})
	public Tribe getTribe() {
		return tribe;
	}

	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

}
