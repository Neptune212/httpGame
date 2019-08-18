package com.tw.httpgame.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Question3 {
	
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
		ToolUsage [] toolUsage;
	}
	
	@Setter
	@Getter
	@ToString
	public static class Output{
		List<ToolsSortedOnUsage> toolsSortedOnUsage;
	}
	
	@Setter
	@Getter
	@ToString
	public static class ToolUsage{
		String name;
		String useStartTime;
		String useEndTime;
	}
	@Setter
	@Getter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ToolsSortedOnUsage {
		String name;
		int timeUsedInMinutes;
	}
}