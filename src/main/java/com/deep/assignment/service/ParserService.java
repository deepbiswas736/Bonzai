package com.deep.assignment.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

	@Value( "${parser.regex}" )
	private String regex;
	
	public void parse(String record) 
    { 
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE); 
        final Matcher matcher = pattern.matcher(record); 
        
        Map<Integer, Integer> countMap = processCount(matcher,8);
        
        Map<Integer, Integer> sortedMap = countMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        
        print(sortedMap);
 
   }
   
	private void print(Map<Integer,Integer> map) {
		map.forEach((k,v)->System.out.println("StatusCode : " + k + " Count : " + v));
	}
	
	private Map<Integer,Integer> processCount(Matcher matcher,Integer groupNo){
		 Map<Integer, Integer> countMap = new HashMap<>(); 
	        Integer count;
	        while (matcher.find()) { 

	            String 	statusCodeString = matcher.group(groupNo); 
	            Integer statusCode = Integer.parseInt(statusCodeString); 

	            count = countMap.get(statusCode);
	            if(count  == null) {
	            	count = 0;
	            }
	            count++;
	            countMap.put(statusCode, count);   
	        } 
	        return countMap;
	}
}
