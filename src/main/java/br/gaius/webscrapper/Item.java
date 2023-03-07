package br.gaius.webscrapper;

import java.math.BigDecimal;
import java.util.regex.*;

public class Item {

	private String modelCode;
	private String modelName;
	private String damageDescription;
	private BigDecimal repairCost;
	
	public Item(String modelCode, String modelName, String damageDescription, BigDecimal repairCost) {
		this.modelCode = modelCode;
		this.modelName = modelName;
		this.damageDescription = damageDescription;
		this.repairCost = repairCost;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public BigDecimal getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(BigDecimal repairCost) {
		this.repairCost = repairCost;
	}
	
	public Item parse(String line) {
		String modelCode;
		String modelName;
		String damageDescription;
		BigDecimal repairCost;
		
		Pattern pattern = Pattern.compile("(.*); (.*); (.*); (\\d+, \\d{2})");
		Matcher matcher = pattern.matcher(line);
		
		if(matcher.find()) {
			modelCode = matcher.group(1);
			modelName = matcher.group(2);
			damageDescription = matcher.group(3);
			repairCost = new BigDecimal(matcher.group(4));
		}else {
			throw new IllegalArgumentException("o texto informado não contém dados dos itens.");
		}
		
		return new Item(modelCode, modelName, damageDescription, repairCost);
	}
	
	@Override
	public String toString() {
		return String.format("%s; %s; %s; %.2f", getModelCode(), getModelName(), 
				getDamageDescription(), getRepairCost());
	}
	
}
