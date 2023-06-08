package kosr.katas.taxes;

import kosr.katas.taxes.models.Order;
import kosr.katas.taxes.models.OrderEntry;
import kosr.katas.taxes.models.Product;
import kosr.katas.taxes.services.TaxCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("java:S106") // désactivation du warning d'utilisation de "System.out" étant donné que toute l'application se base dessus (une autre solution serait d'utiliser un framework de logging)
public class App {

	private static final String INPUT_SEPARATOR = "#### Input";
	private static final String OUTPUT_SEPARATOR = "#### Output";

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			processInConsoleMode();
		} else {
			processInFileMode(args[0]);
		}
	}

	static String prepareOutput(Order order) {
		return Stream
				.of(
						order.getEntries().stream().map(App::formatOrderEntry),
						Stream.of(String.format("%nMontant des taxes : %s€", order.getTaxOnlyTotal())),
						Stream.of(String.format("%nTotal : %s€", order.getAllTaxesIncludedTotal()))
				)
				.flatMap(Function.identity())
				.collect(Collectors.joining(System.lineSeparator()));
	}

	private static String formatOrderEntry(OrderEntry orderEntry) {
		return String.format("%s : %s€", orderEntry.getProduct().getRawInput(), orderEntry.getAllTaxesIncludedPrice());
	}

	private static void processInFileMode(String filePath) throws IOException {
		final var products = loadProducts(filePath);
		final var order = createOrder(products);
		System.out.println(OUTPUT_SEPARATOR);
		printOutput(order);
	}

	private static void processInConsoleMode() {
		final var products = readProducts();
		final var order = createOrder(products);
		printOutput(order);
	}

	private static Order createOrder(List<Product> products) {
		return new Order(
				products.stream()
						.map(product -> new OrderEntry(product, TaxCalculator.calculate(product)))
						.collect(Collectors.toList())
		);
	}

	private static void printOutput(Order order) {
		final var output = prepareOutput(order);
		System.out.println(output);
	}

	private static List<Product> readProducts() {
		System.out.println("Pour terminer la saisie, entrer '#### Output'");
		System.out.println(INPUT_SEPARATOR);
		final var scanner = new Scanner(System.in);
		final var products = new ArrayList<Product>();
		var inputLine = "";
		while (!OUTPUT_SEPARATOR.equals(inputLine = scanner.nextLine())) {
			products.add(new Product(inputLine));
		}
		return products;
	}

	private static List<Product> loadProducts(String filePath) throws IOException {
		System.out.println(INPUT_SEPARATOR);
		try (Stream<String> input = Files.lines(Paths.get(filePath))) {
			return input
					// J'ai utilisé .peek() pour simplifier la lecture du code.
					// Je suis bien conscient que son utilisation n'est pas conseillée
					// et l'autre solution serait de déplacer le print de la ligne dans la méthode .map()
					.peek(System.out::println)
					.map(Product::new)
					.collect(Collectors.toList());
		}
	}

}
