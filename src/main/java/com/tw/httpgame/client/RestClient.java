package com.tw.httpgame.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.httpgame.model.Output;
import com.tw.httpgame.model.Question;
import com.tw.httpgame.model.Question.Input;
import com.tw.httpgame.model.Question2;
import com.tw.httpgame.model.Question3;
import com.tw.httpgame.model.Question3.ToolsSortedOnUsage;
import com.tw.httpgame.model.Question4;
import com.tw.httpgame.service.GameService;

public class RestClient {

	private final static RestTemplate restTemplate = new RestTemplate();
	private final static HttpHeaders headers ;
	private final static HttpEntity requestEntity ;
	private final static String challengeUrl = "https://http-hunt.thoughtworks-labs.net/challenge";
	private final static String inputUrl = "https://http-hunt.thoughtworks-labs.net/challenge/input";
	private final static String outputUrl = "https://http-hunt.thoughtworks-labs.net/challenge/output";
	
	static{
		headers = new HttpHeaders();
		headers.set("userId", "qeO6kOk7l");
		headers.set("content-type", "application/json");
		requestEntity = new HttpEntity(null, headers);
	}
	/*********************** STAGE 1 *********************************************************/
	public static void getChallengeStage() throws URISyntaxException {
		URI uri = new URI(challengeUrl);
		ResponseEntity<Question> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Question.class);
		System.out.println("sample challenge1 is : " + result.getBody());
		postSampleAnswer(result.getBody().getSampleInput().getInput());
	}
	
	private static void postSampleAnswer(Input input) {
		String output = GameService.decodeMessage(input);
		System.out.println("sample output1 is "+output);
	}
	
	public static void getQuestion() throws URISyntaxException {
		URI uri = new URI(inputUrl);
		ResponseEntity<Input> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Input.class);
		System.out.println("input is : "+result.getBody());
		postAnswer(result.getBody());
	}
	
	public static void postAnswer(Input input) throws URISyntaxException {
		URI uri = new URI(outputUrl);
		String output = GameService.decodeMessage(input);
		System.out.println("decoded message1 is "+output);
		
		Output response=new Output();
		response.setMessage(output);
		
		HttpEntity<Output> request = new HttpEntity<>(response, headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("post response1 code : "+result.getStatusCodeValue());

	}
	/*********************** STAGE 2 *********************************************************/
	public static void getChallengeStage2() throws URISyntaxException {
		URI uri = new URI(challengeUrl);
		ResponseEntity<Question2> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Question2.class);
		System.out.println("sample challenge2 is : " + result.getBody());
		postSampleAnswer2(result.getBody().getSampleInput().getInput());
	}
	
	private static void postSampleAnswer2(com.tw.httpgame.model.Question2.Input input) {
		List<String> output = GameService.decodeTools(input);
		System.out.println("sample output2 is "+output);
	}
	
	public static void getQuestion2() throws URISyntaxException {
		URI uri = new URI(inputUrl);
		ResponseEntity<com.tw.httpgame.model.Question2.Input> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, com.tw.httpgame.model.Question2.Input.class);
		System.out.println("input2 is : "+result.getBody());
		postAnswer2(result.getBody());
	}
	
	public static void postAnswer2(com.tw.httpgame.model.Question2.Input input) throws URISyntaxException {
		URI uri = new URI(outputUrl);
		List<String> output = GameService.decodeTools(input);
		System.out.println("decoded message2 is "+output);
		
		com.tw.httpgame.model.Question2.Output response=new com.tw.httpgame.model.Question2.Output();
		response.setToolsFound(output);
		
		HttpEntity<com.tw.httpgame.model.Question2.Output> request = new HttpEntity<>(response, headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("post response2 code : "+result.getStatusCodeValue());

	}
	/*********************** STAGE 3 *********************************************************/
	
	public static void getChallengeStage3() throws URISyntaxException, ParseException {
		URI uri = new URI(challengeUrl);
		ResponseEntity<Question3> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Question3.class);
		System.out.println("sample challenge3 is : " + result.getBody());
		postSampleAnswer3(result.getBody().getSampleInput().getInput());
	}
	
	private static void postSampleAnswer3(com.tw.httpgame.model.Question3.Input input) throws ParseException {
		List<ToolsSortedOnUsage> output = GameService.sortToolsOnUsage(input);
		System.out.println("sample output3  after is "+output);
	}
	
	public static void getQuestion3() throws URISyntaxException, ParseException {
		URI uri = new URI(inputUrl);
		ResponseEntity<com.tw.httpgame.model.Question3.Input> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, com.tw.httpgame.model.Question3.Input.class);
		System.out.println("input3 is : "+result.getBody());
		postAnswer3(result.getBody());
	}
	
	public static void postAnswer3(com.tw.httpgame.model.Question3.Input input) throws URISyntaxException, ParseException {
		URI uri = new URI(outputUrl);
		List<ToolsSortedOnUsage> output = GameService.sortToolsOnUsage(input);
		System.out.println("decoded message3 is "+output);
		
		com.tw.httpgame.model.Question3.Output response=new com.tw.httpgame.model.Question3.Output();
		response.setToolsSortedOnUsage(output);
		
		HttpEntity<com.tw.httpgame.model.Question3.Output> request = new HttpEntity<>(response, headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("post response3 code : "+result.getStatusCodeValue());

	}
	/*********************** STAGE 4 *********************************************************/
	public static void getChallengeStage4() throws URISyntaxException, ParseException {
		URI uri = new URI(challengeUrl);
		ResponseEntity<Question4> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Question4.class);
		System.out.println("sample challenge4 is : " + result.getBody());
		postSampleAnswer4(result.getBody().getSampleInput().getInput());
	}
	
	private static void postSampleAnswer4(com.tw.httpgame.model.Question4.Input input) throws ParseException {
		List<String> output = GameService.toolsToTakeSorted(input);
		System.out.println("sample output4  after is "+output);
	}
	
	public static void getQuestion4() throws URISyntaxException, ParseException {
		URI uri = new URI(inputUrl);
		ResponseEntity<com.tw.httpgame.model.Question4.Input> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, com.tw.httpgame.model.Question4.Input.class);
		System.out.println("input4 is : "+result.getBody());
		postAnswer4(result.getBody());
	}
	
	public static void postAnswer4(com.tw.httpgame.model.Question4.Input input) throws URISyntaxException, ParseException {
		URI uri = new URI(outputUrl);
		List<String> output = GameService.toolsToTakeSorted(input);
		System.out.println("decoded message4 is "+output);
		
		com.tw.httpgame.model.Question4.Output response=new com.tw.httpgame.model.Question4.Output();
		response.setToolsToTakeSorted(output);
		
		HttpEntity<com.tw.httpgame.model.Question4.Output> request = new HttpEntity<>(response, headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("post response4 code : "+result.getStatusCodeValue());

	}

	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		
		String fileName="C:\\Users\\jtrip1\\Desktop\\question.json";
		List<String> list = new ArrayList<>();
		StringBuffer sb=new StringBuffer();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(sb);
		ObjectMapper mapper=new ObjectMapper();
		Question4 ques=mapper.readValue(sb.toString(),Question4.class);
		System.out.println(ques);
	}


}
