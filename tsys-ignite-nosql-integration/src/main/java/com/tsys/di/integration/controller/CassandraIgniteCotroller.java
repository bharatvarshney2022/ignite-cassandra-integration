package com.tsys.di.integration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsys.di.integration.model.Twitter;
import com.tsys.di.integration.repository.ignite.IgniteRepository;

@RestController
public class CassandraIgniteCotroller {

	@RequestMapping(value="/fetch/cassandra/ignite")
	public List<String> getCassandraDataUsingIgnite() {
		List<String> tweetLs =  new ArrayList<>();
		long start_time = System.nanoTime();
		List<List<?>> tweetsList = IgniteRepository.selectFromIgnite();
		for (List<?> list : tweetsList) {
			tweetLs.add(list.get(0).toString() + "        " +list.get(2).toString());
			
		}
		long end_time = System.nanoTime();
		double difference = (end_time - start_time) / 1e6;
		Twitter tweet = new Twitter();
		tweet.setId(101);
		System.out.println(difference);
		tweetLs.add("101                 <----------------------------->"  + String.valueOf(difference)
		+ "<----------------------------->");
		
		return tweetLs;

	}
	
	@RequestMapping(value="/fetch/cassandra/ignite/{id}")
	public List<String> getCassandraDataUsingIgnite(@PathVariable("id") long id) {
		List<String> tweetLs =  new ArrayList<>();
		long start_time = System.nanoTime();
		List<List<?>> tweetsList = IgniteRepository.selectFromIgniteById(id);
		for (List<?> list : tweetsList) {
			tweetLs.add(list.get(0).toString() + "        " +list.get(2).toString());
			
		}
		long end_time = System.nanoTime();
		double difference = (end_time - start_time) / 1e6;
		Twitter tweet = new Twitter();
		tweet.setId(101);
		System.out.println(difference);
		tweetLs.add("101                 <----------------------------->"  + String.valueOf(difference)
		+ "<----------------------------->");
	
		return tweetLs;
	}
	
	@RequestMapping(value="/put/cassandra/ignite/{id}/{message}")
	public String getCassandraDataUsingIgnite(@PathVariable("id") long id, @PathVariable String message ) {
		Twitter tweet = new Twitter();
		tweet.setId(id);
		tweet.setText(message);
		String result = IgniteRepository.insertFromIngnite(id, tweet);
		return result;
	}
	

}
