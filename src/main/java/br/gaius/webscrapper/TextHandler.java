package br.gaius.webscrapper;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.regex.*;

public class TextHandler {

	private static Pattern getPattern(String regex) {
		return Pattern.compile(regex);
	}

	public static String clean(String content) {

		StringBuilder patternBuilder = new StringBuilder();
		StringBuilder outputBuilder = new StringBuilder();

		patternBuilder.append("'modelCode': '(.*)',|");
		patternBuilder.append("'modelName': '(.*)',|");
		patternBuilder.append("'damageDescription': '(.*)',|");
		patternBuilder.append("'repairCost': '(.*)'");
		
		Pattern pattern = getPattern(patternBuilder.toString());
		Matcher matcher = pattern.matcher(content);
		
		final int PROPERTIES_LENGTH = 4;
		int i = 0;
		
		while (matcher.find()) {
			outputBuilder.append(matcher.group());

			if (++i % PROPERTIES_LENGTH == 0) {
				outputBuilder.append('\n');
			}
		}

		return outputBuilder.toString();
	}

	public static String getValues(String content) {
		StringBuilder patternBuilder = new StringBuilder();
		StringBuilder outputBuilder = new StringBuilder();

		patternBuilder.append("'modelCode': '(.*)',");
		patternBuilder.append("'modelName': '(.*)',");
		patternBuilder.append("'damageDescription': '(.*)',");
		patternBuilder.append("'repairCost': '(.*)'");

		Pattern pattern = getPattern(patternBuilder.toString());
		Matcher matcher = pattern.matcher(content);
		
		final int PROPERTIES_LENGTH = 4;
		
		while(matcher.find()) {
			for(int i = 1; i <= matcher.groupCount(); i++) {
				if(i == matcher.groupCount()) {
					outputBuilder.append(matcher.group(i) + "\n");
				}else {
					outputBuilder.append(matcher.group(i) + "; ");
				}
			}
			
		}
		
		return outputBuilder.toString();
	}
	
	public static boolean createFile(String fileName) {

		Path filePath = Paths.get(System.getProperty("user.home"), "Downloads", fileName);
		
		try (Formatter formatter = new Formatter(filePath.toFile())){
			formatter.format("%s", "saitou san!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return Files.exists(filePath);
	}
	
	public static String renameFile() {
		String fileName = "tabela-samsung";
		LocalDate date = LocalDate.now();
		String extension = "csv";
		
		return String.format("%s %s.%s", fileName, date.toString(), extension);
	}
}
