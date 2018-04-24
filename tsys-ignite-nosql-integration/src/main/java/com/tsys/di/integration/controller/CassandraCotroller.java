package com.tsys.di.integration.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsys.di.integration.model.Twitter;
import com.tsys.di.integration.repository.cassandra.CassandraRepository;
import com.tsys.di.integration.repository.ignite.IgniteRepository;

@RestController
public class CassandraCotroller {

	private static CassandraRepository cassandraRepository = new CassandraRepository("127.0.0.1", 9042);

	@RequestMapping(value = "/fetch/cassandra/")
	public List<Twitter> getCassandraDataUsingIgnite() {

		long start_time = System.nanoTime();
		List<Twitter> tweetsList = cassandraRepository.selectTweet();

		long end_time = System.nanoTime();
		double difference = (end_time - start_time) / 1e6;
		Twitter tweet = new Twitter();
		tweet.setId(101);
		tweet.setText("<----------------------------->"  + String.valueOf(difference)
		+ "<----------------------------->");
		System.out.println(difference);
		tweetsList.add(tweet);
		return tweetsList;

	}

	@RequestMapping(value = "/fetch/cassandra/{id}")
	public List<Twitter> getCassandraDataUsingIgnite(@PathVariable("id") long id) {
	
		long start_time = System.nanoTime();
		List<Twitter> tweetsList = cassandraRepository.selectTweetById(id);
		long end_time = System.nanoTime();
		double difference = (end_time - start_time) / 1e6;
		Twitter tweet = new Twitter();
		tweet.setId(101);
		tweet.setText("<----------------------------->"  + String.valueOf(difference)
		+ "<----------------------------->");
		System.out.println(difference);
		tweetsList.add(tweet);
		
		return tweetsList;
	}

	// @RequestMapping(value="/put/cassandra/ignite/{id}/{message}")
	public String getCassandraDataUsingIgnite(@PathVariable("id") long id, @PathVariable String message) {
		Twitter tweet = new Twitter();
		tweet.setId(Integer.parseInt(String.valueOf(id)));
		tweet.setText(message);
		String result = IgniteRepository.insertFromIngnite(id, tweet);
		return result;
	}

}
