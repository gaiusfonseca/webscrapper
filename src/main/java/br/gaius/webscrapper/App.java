package br.gaius.webscrapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class App{
	
	final static String URL = "https://www.samsung.com/br/support/valorreparo/";
	final static int SCRIPT_INDEX = 9;
	
	public static void main(String[] args) {
		//get the html document from the specified url
		Document document = null;
		
		try {
			document = Jsoup.connect(URL).get();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//select the desired element
		Element script = document.getElementsByTag("script").get(SCRIPT_INDEX);
		
		//capture the content of the element
		String content = script.data();
		
		//removes unecessary data
		TextHandler textHandler = new TextHandler();
		content = TextHandler.clean(content);
		content = TextHandler.getValues(content);
		
		//parse data into item objects
		TextHandler.createFile(content);
		
		//put item objects into a list
		
		//generate the csv file from the list
	}
	
}