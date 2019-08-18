package com.tw.httpgame.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Question {
	
	private String stage;
	private String statement;
	private String instructions;
	private SampleInput sampleInput;
	private SampleOutput sampleOutput;
	
	@Setter
	@Getter
	@ToString
	public static class SampleInput{
		Input input;
	}
	
	@Setter
	@Getter
	@ToString
	public static class SampleOutput{
		Output output;
	}
	
	@Setter
	@Getter
	@ToString
	public static class Input{
		private String encryptedMessage;
		private int key;
		
	}

	
}
