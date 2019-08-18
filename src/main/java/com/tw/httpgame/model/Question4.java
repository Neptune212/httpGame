package com.tw.httpgame.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Question4 {
	
	private String stage;
	private String statement;
	private String instructions;
	private SampleInput sampleInput;
	private SampleOutput sampleOutput;
	
	@Setter
	@Getter
	@ToString
	public static class SampleOutput{
		Output output;
	}
	
	@Setter
	@Getter
	@ToString
	public static class SampleInput{
		Input input;
	}
	
	@Setter
	@Getter
	@ToString
	public static class Input{
		Tool [] tools;
		int maximumWeight;
	}
	
	@Setter
	@Getter
	@ToString
	public static class Output{
		List<String> toolsToTakeSorted;
	}
	
	@Setter
	@Getter
	@ToString
	public static class Tool{
		String name;
		int weight;
		int value;
	}
	
}