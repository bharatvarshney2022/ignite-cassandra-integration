package com.nextgen.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.nextgen.integration.ignite.loader.IgniteLoader;
import com.nextgen.integration.twitter.stream.TwitterStreaming;

@SpringBootApplication
@ComponentScan
public class ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
		IgniteLoader.startIgnite(args);
		//TwitterStreaming.twitterStreamer();
	}
}
