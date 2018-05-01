package com.nextgen.integration.model;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Twitter implements Serializable {

	private static final long serialVersionUID = -5350789537473512833L;
	
	@QuerySqlField
	private long id;
	@QuerySqlField
	private String created_at;
	@QuerySqlField
	private String text;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
