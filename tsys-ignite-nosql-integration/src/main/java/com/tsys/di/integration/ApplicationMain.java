package com.tsys.di.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tsys.di.integration.ignite.loader.IgniteLoader;
import com.tsys.di.integration.twitter.stream.TwitterStreaming;

@SpringBootApplication
@ComponentScan
public class ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
		IgniteLoader.startIgnite(args);
		TwitterStreaming.twitterStreamer();
	}
}
