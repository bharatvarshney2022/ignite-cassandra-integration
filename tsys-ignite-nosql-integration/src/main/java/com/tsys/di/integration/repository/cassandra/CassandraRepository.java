package com.tsys.di.integration.repository.cassandra;

import static java.lang.System.out;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.tsys.di.integration.connector.cassandra.CassandraConnector;
import com.tsys.di.integration.connector.cassandra.DBConnector;
import com.tsys.di.integration.model.Twitter;

public class CassandraRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	private final DBConnector client = new CassandraConnector();

	public CassandraRepository(final String newHost, final int newPort) {
		out.println("Connecting to IP Address " + newHost + ":" + newPort + "...");
		client.connect(newHost, newPort);
	}

	
	public void persistTwitter(final long id, final String created_at,  final String text) {
		client.getSession().execute(
				"INSERT INTO tweet.twitter (id, created_at, text) VALUES (?, ?, ?)"
				, id, created_at, text);
	}
	
	public void persistTwitterSourceInfo(final long id,  final String source, final String user) {
		client.getSession().execute(
				"INSERT INTO  tweet.twitterSourceData (id, parent_id, source, user) "
				+ "VALUES (?, ?, ?, ?)", id , id,
				source, user);
	}
	
	public List<Twitter> selectTweetById(final long id) {
		ResultSet execute = client.getSession().execute(
				"select * from tweet.twitter where id = " + id );
		List<Twitter> tweets =  new ArrayList<>();
		List<Row> all = execute.all();
		for (Row row : all) {
			Twitter tweet = new Twitter();
			tweets.add(tweet);
			tweet.setId(row.getLong("id"));
			tweet.setText(row.getString("text"));
			
		}
		
		return tweets;
		
	}

	public List<Twitter> selectTweet() {
		ResultSet execute = client.getSession().execute(
				"select * from tweet.twitter limit 1000");
		List<Twitter> tweets =  new ArrayList<>();
		List<Row> all = execute.all();
		for (Row row : all) {
			Twitter tweet = new Twitter();
			tweets.add(tweet);
			tweet.setId(row.getLong("id"));
			tweet.setText(row.getString("text"));
			
		}
		
		return tweets;
	}
}
