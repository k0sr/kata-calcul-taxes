package kosr.katas.taxes.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class Order {

	private final List<OrderEntry> entries;

	public Order(List<OrderEntry> entries) {
		this.entries = entries;
	}

	public BigDecimal getTaxOnlyTotal() {
		return getTotal(OrderEntry::getTaxOnly);
	}

	public BigDecimal getAllTaxesIncludedTotal() {
		return getTotal(OrderEntry::getAllTaxesIncludedPrice);
	}

	public List<OrderEntry> getEntries() {
		return entries;
	}

	private BigDecimal getTotal(Function<OrderEntry, BigDecimal> retrieveTotal) {
		return entries.stream().map(retrieveTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
