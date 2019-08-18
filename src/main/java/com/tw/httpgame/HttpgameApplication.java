package com.tw.httpgame;

import java.net.URISyntaxException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tw.httpgame.client.RestClient;

@SpringBootApplication
public class HttpgameApplication {

	public static void main(String[] args) throws URISyntaxException, ParseException {
		SpringApplication.run(HttpgameApplication.class, args);
		//RestClient.getChallengeStage4();
		RestClient.getQuestion4();
	}

}
