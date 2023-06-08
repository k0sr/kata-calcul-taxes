package kosr.katas.taxes.models;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Product {

	private static final Pattern IMPORTED_PRODUCT_PATTERN = Pattern.compile(".*importée?s?$");
	private static final Pattern PRODUCT_DETAILS_PATTERN = Pattern.compile("^\\*\\s(\\d+)\\s(.*)\\sà\\s(\\d*\\.?\\d*)€$");

	private final String rawInput;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private ProductCategory category;
	private boolean isImported;

	public Product(String rawInput) {
		this.rawInput = rawInput;
		parse();
	}

	private static boolean checkIfImported(String productLabel) {
		return IMPORTED_PRODUCT_PATTERN.matcher(productLabel).matches();
	}

	private void parse() {
		final var productDetailsMatcher = PRODUCT_DETAILS_PATTERN.matcher(rawInput);
		if (productDetailsMatcher.find()) {
		    quantity =  new BigDecimal(productDetailsMatcher.group(1));
			unitPrice = new BigDecimal(productDetailsMatcher.group(3));
			final var label = productDetailsMatcher.group(2);
			category = ProductCategory.from(label);
			isImported = checkIfImported(label);
		} else {
			throw new IllegalArgumentException("Invalid input line");
		}
	}

	public String getRawInput() {
		return rawInput;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public boolean isImported() {
		return isImported;
	}
}
