package com.nextgen.integration.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class TwitterSource {
	//@QuerySqlField
	private long id;
	//@QuerySqlField
	private long parent_id;
	//@QuerySqlField
	private String source;
	//@QuerySqlField
	private String user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}