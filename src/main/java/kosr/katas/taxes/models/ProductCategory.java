package kosr.katas.taxes.models;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum ProductCategory {

	ESSENTIAL("^(boîtes?\\sde\\schocolats.*|barres?\\sde\\schocolats?.*|boîtes?\\sde\\spilules\\scontre\\sla\\smigraine.*)", 0),
	BOOK("^livres?.*", 10),
	OTHER(".*", 20);

	private final Pattern categoryPattern;
	private final int tax;

	ProductCategory(String categoryPattern, int tax) {
		this.categoryPattern = Pattern.compile(categoryPattern);
		this.tax = tax;
	}

	static ProductCategory from(String productLabel) {
		return Arrays.stream(values())
					 .filter(cat -> cat.categoryPattern.matcher(productLabel).matches())
					 .findFirst()
					 .orElseThrow(() -> {
						 throw new IllegalArgumentException("Unable to detect the product category from its label " + productLabel);
					 });
	}

	public int getTax() {
		return tax;
	}
}
