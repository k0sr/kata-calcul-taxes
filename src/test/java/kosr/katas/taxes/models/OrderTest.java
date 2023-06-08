package kosr.katas.taxes.models;

import kosr.katas.taxes.services.TaxCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

	@Test
	void shouldCreateFirstOrder() {
		// given
		final var productOne = new Product("* 2 livres à 12.49€");
		final var productTwo = new Product("* 1 CD musical à 14.99€");
		final var productThree = new Product("* 3 barres de chocolat à 0.85€");
		final var orderEntries = List.of(
				new OrderEntry(productOne, TaxCalculator.calculate(productOne)),
				new OrderEntry(productTwo, TaxCalculator.calculate(productTwo)),
				new OrderEntry(productThree, TaxCalculator.calculate(productThree))
		);

		// when
		final var order = new Order(orderEntries);

		// then
		assertEquals(new BigDecimal("5.50"), order.getTaxOnlyTotal());
		assertEquals(new BigDecimal("48.02"), order.getAllTaxesIncludedTotal());
	}

	@Test
	void shouldCreateSecondOrder() {
		// given
		final var productOne = new Product("* 2 boîtes de chocolats importée à 10€");
		final var productTwo = new Product("* 3 flacons de parfum importé à 47.50€");
		final var orderEntries = List.of(
				new OrderEntry(productOne, TaxCalculator.calculate(productOne)),
				new OrderEntry(productTwo, TaxCalculator.calculate(productTwo))
		);

		// when
		final var order = new Order(orderEntries);

		// then
		assertEquals(new BigDecimal("36.70"), order.getTaxOnlyTotal());
		assertEquals(new BigDecimal("199.20"), order.getAllTaxesIncludedTotal());
	}

	@Test
	void shouldCreateThirdOrder() {
		// given
		final var productOne = new Product("* 2 flacons de parfum importé à 27.99€");
		final var productTwo = new Product("* 1 flacon de parfum à 18.99€");
		final var productThree = new Product("* 3 boîtes de pilules contre la migraine à 9.75€");
		final var productFour = new Product("* 2 boîtes de chocolats importés à 11.25€");
		final var orderEntries = List.of(
				new OrderEntry(productOne, TaxCalculator.calculate(productOne)),
				new OrderEntry(productTwo, TaxCalculator.calculate(productTwo)),
				new OrderEntry(productThree, TaxCalculator.calculate(productThree)),
				new OrderEntry(productFour, TaxCalculator.calculate(productFour))
		);

		// when
		final var order = new Order(orderEntries);

		// then
		assertEquals(new BigDecimal("19.00"), order.getTaxOnlyTotal());
		assertEquals(new BigDecimal("145.72"), order.getAllTaxesIncludedTotal());
	}
}
