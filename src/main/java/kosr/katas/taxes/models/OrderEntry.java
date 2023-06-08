package kosr.katas.taxes.models;

import java.math.BigDecimal;

public class OrderEntry {

	private final Product product;

	private final BigDecimal unitTax;

	public OrderEntry(Product product, BigDecimal unitTax) {
		this.product = product;
		this.unitTax = unitTax;
	}

	public Product getProduct() {
		return product;
	}

	public BigDecimal getTaxOnly() {
		return unitTax.multiply(product.getQuantity());
	}

	public BigDecimal getAllTaxesIncludedPrice() {
		return unitTax.add(product.getUnitPrice()).multiply(product.getQuantity());
	}

}
