package com.tw.httpgame.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.tw.httpgame.model.Question.Input;
import com.tw.httpgame.model.Question3.ToolUsage;
import com.tw.httpgame.model.Question3.ToolsSortedOnUsage;
import com.tw.httpgame.model.Question4.Tool;

public class GameService {

	public static String decodeMessage(Input input) {
		String encryptedMessage=input.getEncryptedMessage();
		//encryptedMessage=encryptedMessage.toLowerCase();
		char[] charArray = encryptedMessage.toCharArray();
		int decimalValue=0;
		int key=input.getKey();
		int diff=0;
		int subFrmBack=0;
		for (int i=0;i<charArray.length;i++) {
			decimalValue=(int)charArray[i];//67
			diff=0;
			subFrmBack=0;
			//uppercase letter
			if(decimalValue>63 && decimalValue<91){
				diff=(decimalValue-key);//62
				if(diff<65){
					subFrmBack=65-diff;//3
					charArray[i]=(char)(91-subFrmBack);//87
				}else{
					charArray[i]=(char)diff;
				}
			}
			//lowercase letter
			if(decimalValue>96 && decimalValue<123){
				diff=(decimalValue-key);
				if(diff<97){
					subFrmBack=97-diff;
					charArray[i]=(char)(123-subFrmBack);
				}else{
					charArray[i]=(char)diff;
				}
			}
		}
		return String.valueOf(charArray);
	}
	
	public static List<String> decodeTools(com.tw.httpgame.model.Question2.Input input) {
		String hiddenTools = input.getHiddenTools();
		String[] tools = input.getTools();
		List<String> foundTools=new ArrayList<>();
		int curr=-1;
		int prev=-1;
		boolean toolFound=true;
		for (String tool : tools) {
			toolFound=true;
			curr=-1;
			prev=-1;
			char[] charArray = tool.toCharArray();
			for (char c : charArray) {
				curr=hiddenTools.indexOf(c,prev);
				if(curr==-1){
					toolFound=false;
					break;
				}
				prev=curr;
			}
			if(!toolFound){
				continue;
			}else{
				foundTools.add(tool);
			}
		}
		return foundTools;
	}

	public static List<ToolsSortedOnUsage> sortToolsOnUsage(com.tw.httpgame.model.Question3.Input input) throws ParseException {
		List<ToolsSortedOnUsage> sortedTools=new ArrayList<>();
		Map<String,Integer>toolTime=new HashMap<>();
		ToolUsage[] toolUsage = input.getToolUsage();
		for (ToolUsage toolUsg : toolUsage) {
			Date toDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(toolUsg.getUseStartTime());
			Date frmDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(toolUsg.getUseEndTime());
			long diffInMillies = Math.abs(frmDate.getTime() - toDate.getTime());
		    int diff = (int) TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if(toolTime.containsKey(toolUsg.getName())){
				toolTime.put(toolUsg.getName(), toolTime.get(toolUsg.getName())+diff);
			}else{
				toolTime.put(toolUsg.getName(), diff);
			}
		}
		for (Entry<String, Integer> entry : toolTime.entrySet()) {
			sortedTools.add(new ToolsSortedOnUsage(entry.getKey(),entry.getValue()));
		}
		sortedTools.sort((t1,t2)->t2.getTimeUsedInMinutes()-t1.getTimeUsedInMinutes());
		return sortedTools;
	}

	public static List<String> toolsToTakeSorted(com.tw.httpgame.model.Question4.Input input) {
		List<String>toolsToTake=new ArrayList<>();
		Map<Integer,List<Tool>>priorityTool=new TreeMap<>();
		int maximumWeight = input.getMaximumWeight();
		Tool[] tools = input.getTools();
		int possSubSets=(int) Math.pow(2, tools.length);
		int maxVal=0;
		for (int i = 0; i < possSubSets; i++) {
			int tempWt=0;
			int tempVal=0;
			List<Tool>maxWtTool=new ArrayList<Tool>();
			//System.out.println();
			for (int j = 0; j < tools.length; j++) {
				if(BigInteger.valueOf(i).testBit(j)){
					tempWt=tempWt+tools[j].getWeight();
					tempVal=tempVal+tools[j].getValue();
					maxWtTool.add(tools[j]);
					//System.out.print(tools[j].getWeight()+" ");
				}
			}
			//System.out.print(" => "+tempWt+" : "+tempVal);
			if(tempWt<=maximumWeight && tempVal>maxVal){
				priorityTool.clear();
				priorityTool.put(tempVal, maxWtTool);
				maxVal=tempVal;
			}else if(tempWt<=maximumWeight && tempVal==maxVal){
				priorityTool.put(tempVal, maxWtTool);
			}
			else{
				maxWtTool=null;
			}
		}
		for (Entry<Integer, List<Tool>> entry : priorityTool.entrySet()) {
			for (Tool tool : entry.getValue()) {
				toolsToTake.add(tool.getName());
			}
			break;
		}
		Collections.sort(toolsToTake);
		return toolsToTake;
	}
}
