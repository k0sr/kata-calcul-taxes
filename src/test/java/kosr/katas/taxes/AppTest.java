package kosr.katas.taxes;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import kosr.katas.taxes.models.Order;
import kosr.katas.taxes.models.OrderEntry;
import kosr.katas.taxes.models.Product;
import kosr.katas.taxes.services.TaxCalculator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

	@Test
	void shouldCreateFirstOrderOutput() {
		// given
		final var productOne = new Product("* 2 livres à 12.49€");
		final var productTwo = new Product("* 1 CD musical à 14.99€");
		final var productThree = new Product("* 3 barres de chocolat à 0.85€");
		final var orderEntries = List.of(
				new OrderEntry(productOne, TaxCalculator.calculate(productOne)),
				new OrderEntry(productTwo, TaxCalculator.calculate(productTwo)),
				new OrderEntry(productThree, TaxCalculator.calculate(productThree))
		);
		final var order = new Order(orderEntries);
		final var expectedOutput = Stream.of(
				"* 2 livres à 12.49€ : 27.48€",
				"* 1 CD musical à 14.99€ : 17.99€",
				"* 3 barres de chocolat à 0.85€ : 2.55€",
				"",
				"Montant des taxes : 5.50€",
				"",
				"Total : 48.02€"
		).collect(Collectors.joining(System.lineSeparator()));

		// when
		final var output = App.prepareOutput(order);

		// then
		assertEquals(expectedOutput, output);
	}

	@Test
	void shouldCreateSecondOrderOutput() {
		// given
		final var productOne = new Product("* 2 boîtes de chocolats importée à 10€");
		final var productTwo = new Product("* 3 flacons de parfum importé à 47.50€");
		final var orderEntries = List.of(
				new OrderEntry(productOne, TaxCalculator.calculate(productOne)),
				new OrderEntry(productTwo, TaxCalculator.calculate(productTwo))
		);
		final var order = new Order(orderEntries);
		final var expectedOutput = Stream.of(
				"* 2 boîtes de chocolats importée à 10€ : 21.00€",
				"* 3 flacons de parfum importé à 47.50€ : 178.20€",
				"",
				"Montant des taxes : 36.70€",
				"",
				"Total : 199.20€"
		).collect(Collectors.joining(System.lineSeparator()));

		// when
		final var output = App.prepareOutput(order);

		// then
		assertEquals(expectedOutput, output);
	}

	@Test
	void shouldCreateThirdOrderOutput() {
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
		final var order = new Order(orderEntries);
		final var expectedOutput = Stream.of(
				"* 2 flacons de parfum importé à 27.99€ : 69.98€",
				"* 1 flacon de parfum à 18.99€ : 22.79€",
				"* 3 boîtes de pilules contre la migraine à 9.75€ : 29.25€",
				"* 2 boîtes de chocolats importés à 11.25€ : 23.70€",
				"",
				"Montant des taxes : 19.00€",
				"",
				"Total : 145.72€"
		).collect(Collectors.joining(System.lineSeparator()));

		// when
		final var output = App.prepareOutput(order);

		// then
		assertEquals(expectedOutput, output);
	}
}
