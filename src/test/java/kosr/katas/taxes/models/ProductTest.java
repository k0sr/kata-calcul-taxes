package kosr.katas.taxes.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

	@Test
	void shouldCreateTwoBooksProduct() {
		// given
		final var rawInput = "* 2 livres à 12.49€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("2"), product.getQuantity());
		assertEquals(new BigDecimal("12.49"), product.getUnitPrice());
		assertEquals(ProductCategory.BOOK, product.getCategory());
		assertFalse(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateOneCdProduct() {
		// given
		final var rawInput = "* 1 CD musical à 14.99€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("1"), product.getQuantity());
		assertEquals(new BigDecimal("14.99"), product.getUnitPrice());
		assertEquals(ProductCategory.OTHER, product.getCategory());
		assertFalse(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateThreeChocolatBarProduct() {
		// given
		final var rawInput = "* 3 barres de chocolat à 0.85€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("3"), product.getQuantity());
		assertEquals(new BigDecimal("0.85"), product.getUnitPrice());
		assertEquals(ProductCategory.ESSENTIAL, product.getCategory());
		assertFalse(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateTwoChocolatBoxProduct() {
		// given
		final var rawInput = "* 2 boîtes de chocolats importée à 10€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("2"), product.getQuantity());
		assertEquals(new BigDecimal("10"), product.getUnitPrice());
		assertEquals(ProductCategory.ESSENTIAL, product.getCategory());
		assertTrue(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateThreeImportedPerfumeProduct() {
		// given
		final var rawInput = "* 3 flacons de parfum importé à 47.50€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("3"), product.getQuantity());
		assertEquals(new BigDecimal("47.50"), product.getUnitPrice());
		assertEquals(ProductCategory.OTHER, product.getCategory());
		assertTrue(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateTwoImportedPerfumeProduct() {
		// given
		final var rawInput = "* 2 flacons de parfum importé à 27.99€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("2"), product.getQuantity());
		assertEquals(new BigDecimal("27.99"), product.getUnitPrice());
		assertEquals(ProductCategory.OTHER, product.getCategory());
		assertTrue(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateOnePerfumeProduct() {
		// given
		final var rawInput = "* 1 flacon de parfum à 18.99€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("1"), product.getQuantity());
		assertEquals(new BigDecimal("18.99"), product.getUnitPrice());
		assertEquals(ProductCategory.OTHER, product.getCategory());
		assertFalse(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateThreePilsBoxProduct() {
		// given
		final var rawInput = "* 3 boîtes de pilules contre la migraine à 9.75€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("3"), product.getQuantity());
		assertEquals(new BigDecimal("9.75"), product.getUnitPrice());
		assertEquals(ProductCategory.ESSENTIAL, product.getCategory());
		assertFalse(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldCreateTwoChocolatBoxesProduct() {
		// given
		final var rawInput = "* 2 boîtes de chocolats importés à 11.25€";

		// when
		final var product = new Product(rawInput);

		// then
		assertEquals(new BigDecimal("2"), product.getQuantity());
		assertEquals(new BigDecimal("11.25"), product.getUnitPrice());
		assertEquals(ProductCategory.ESSENTIAL, product.getCategory());
		assertTrue(product.isImported());
		assertEquals(rawInput, product.getRawInput());
	}

	@Test
	void shouldThrowExceptionWhenInputRawIsInvalid() {
		// given
		final var rawInput = "INVALID INPUT RAW";

		// when
		final Executable executable = () -> new Product(rawInput);

		// then
		final var exception = assertThrows(IllegalArgumentException.class, executable);
		assertEquals("Invalid input line", exception.getMessage());
	}

}
