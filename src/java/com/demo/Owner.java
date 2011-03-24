package com.demo;

import org.hibernate.search.annotations.*;
import javax.persistence.*;

@Indexed
@Table(name="OWNER")
@Entity
public class Owner {	
    @Id @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	@Field
	private String name;
	@Version
	private long version = 0;	
	
	public Long getId(){
		return id;
	}			
	public String getName(){
		return name;
	}
	public long getVersion(){
		return version;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setVersion(long version){
		this.version = version;
	}
}