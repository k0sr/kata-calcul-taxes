package kosr.katas.taxes.services;

import kosr.katas.taxes.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorTest {

	@ParameterizedTest
	@MethodSource
	void roundShouldReturnRoundedValue(BigDecimal inputValue, BigDecimal expectedValue) {
		// when
		final var roundedValue = TaxCalculator.round(inputValue);

		// then
		assertEquals(expectedValue, roundedValue);
	}

	@Test
	void calculateShouldReturnTaxWithoutImportFees() {
		// given
		final var product = new Product("* 3 flacons de parfum à 47.50€");

		// when
		final var calculatedTax = TaxCalculator.calculate(product);

		// then
		assertEquals(new BigDecimal("9.50"), calculatedTax);
	}

	@Test
	void calculateShouldReturnTaxWithImportFees() {
		// given
		final var product = new Product("* 3 flacons de parfum à 47.50€ importés à 47.50€");

		// when
		final var calculatedTax = TaxCalculator.calculate(product);

		// then
		assertEquals(new BigDecimal("11.90"), calculatedTax);
	}

	private static Stream<Arguments> roundShouldReturnRoundedValue() {
		return Stream.of(
				Arguments.of(new BigDecimal("0.95"), new BigDecimal("0.95")),
				Arguments.of(new BigDecimal("0.99"), new BigDecimal("1.00")),
				Arguments.of(new BigDecimal("1.00"), new BigDecimal("1.00")),
				Arguments.of(new BigDecimal("1.01"), new BigDecimal("1.05")),
				Arguments.of(new BigDecimal("1.02"), new BigDecimal("1.05"))
		);
	}
}
