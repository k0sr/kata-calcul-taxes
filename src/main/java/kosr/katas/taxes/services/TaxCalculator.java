package kosr.katas.taxes.services;

import kosr.katas.taxes.models.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {

	private static final BigDecimal IMPORT_TAX = new BigDecimal(5);

	private TaxCalculator() {
		// hidden constructor
	}

	public static BigDecimal calculate(Product product) {
		final var vat = product.getUnitPrice().multiply(new BigDecimal(product.getCategory().getTax())).divide(new BigDecimal(100));
		final var importTax = product.isImported() ? (product.getUnitPrice().multiply(IMPORT_TAX)).divide(new BigDecimal(100)) : BigDecimal.ZERO;
		return round(vat).add(round(importTax));
	}

	static BigDecimal round(BigDecimal value) {
		final var twoDecimalsValue = value.setScale(2, RoundingMode.CEILING);
		final var roundedValue = (int) ((twoDecimalsValue.floatValue() * 100) + 4) / 5 * 5;
		return BigDecimal.valueOf((float) roundedValue / 100).setScale(2, RoundingMode.HALF_UP);
	}
}
