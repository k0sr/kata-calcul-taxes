package kosr.katas.taxes.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryTest {

	@ParameterizedTest
	@MethodSource
	void fromShouldReturnCalculatedCategory(String label, ProductCategory expectedCategory) {
		// when
		final var category = ProductCategory.from(label);

		// then
		assertEquals(expectedCategory, category);
	}

	private static Stream<Arguments> fromShouldReturnCalculatedCategory() {
		return Stream.of(
				Arguments.of("livres", ProductCategory.BOOK),
				Arguments.of("CD musical", ProductCategory.OTHER),
				Arguments.of("barres de chocolat", ProductCategory.ESSENTIAL),
				Arguments.of("boîtes de chocolats importée", ProductCategory.ESSENTIAL),
				Arguments.of("flacons de parfum importé", ProductCategory.OTHER),
				Arguments.of("flacons de parfum importé", ProductCategory.OTHER),
				Arguments.of("flacon de parfum", ProductCategory.OTHER),
				Arguments.of("boîtes de pilules contre la migraine", ProductCategory.ESSENTIAL),
				Arguments.of("boîtes de chocolats importés", ProductCategory.ESSENTIAL)
		);
	}

}
