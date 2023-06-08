package kosr.katas.taxes.models;

import kosr.katas.taxes.services.TaxCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderEntryTest {

	@ParameterizedTest
	@MethodSource
	void shouldCreateNewOrderEntry(String productLabel, BigDecimal taxOnly, BigDecimal allTaxesIncludedPrice) {
		// given
		final var product = new Product(productLabel);
		final var unitTax = TaxCalculator.calculate(product);

		// when
		final var orderEntry = new OrderEntry(product, unitTax);

		// then
		assertEquals(taxOnly, orderEntry.getTaxOnly());
		assertEquals(allTaxesIncludedPrice, orderEntry.getAllTaxesIncludedPrice());
	}

	private static Stream<Arguments> shouldCreateNewOrderEntry() {
		return Stream.of(
				Arguments.of("* 2 livres à 12.49€", new BigDecimal("2.50"), new BigDecimal("27.48")),
				Arguments.of("* 1 CD musical à 14.99€", new BigDecimal("3.00"), new BigDecimal("17.99")),
				Arguments.of("* 3 barres de chocolat à 0.85€", new BigDecimal("0.00"), new BigDecimal("2.55")),
				Arguments.of("* 2 boîtes de chocolats importée à 10€", new BigDecimal("1.00"), new BigDecimal("21.00")),
				Arguments.of("* 3 flacons de parfum importé à 47.50€", new BigDecimal("35.70"), new BigDecimal("178.20")),
				Arguments.of("* 2 flacons de parfum importé à 27.99€", new BigDecimal("14.00"), new BigDecimal("69.98")),
				Arguments.of("* 1 flacon de parfum à 18.99€", new BigDecimal("3.80"), new BigDecimal("22.79")),
				Arguments.of("* 3 boîtes de pilules contre la migraine à 9.75€", new BigDecimal("0.00"), new BigDecimal("29.25")),
				Arguments.of("* 2 boîtes de chocolats importés à 11.25€", new BigDecimal("1.20"), new BigDecimal("23.70"))
		);
	}

}
