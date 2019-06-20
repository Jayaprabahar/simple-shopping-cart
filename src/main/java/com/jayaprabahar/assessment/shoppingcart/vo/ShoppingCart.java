/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : ShoppingCart.java </p>
 * <p> Description: ShoppingCart contains the individual shoppers(based on shopperId) shopping cart and total price of the products</p>
 * <p> Created: Mar 24, 2019</p>
 * 
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class ShoppingCart {

	List<ShoppingItem> shoppingItems;
	int totalUnits;
	BigDecimal totalPrice;
	BigDecimal salesTaxAmount;
	BigDecimal salesTaxPercent;

	public ShoppingCart updateShoppingCart(Product product, int unitCount, Action shopperAction) {
		Optional<ShoppingItem> shopItem = shoppingItems.stream().filter(e -> e.getProduct().equals(product)).findFirst();

		if (shopItem.isPresent())
			shopItem.get().updateItemCount(unitCount * (shopperAction.equals(Action.ADD) ? 1 : -1));
		else
			shoppingItems.add(new ShoppingItem(product, unitCount));

		// Remove products with less than or equal to 0 units. This happens when users add and removes the
		// same number of units for a specific product
		shoppingItems.removeIf(e -> e.getItemCount() <= 0);

		// Recalculate total price and count
		totalPrice = BigDecimal.ZERO;
		totalUnits = 0;

		if (!CollectionUtils.isNotEmpty(shoppingItems)) {
			shoppingItems.stream().forEach(e -> {
				totalUnits += e.getItemCount();
				totalPrice.add(e.getTotalPricePerItem());
			});
		}
		return this;
	}

	/**
	 * This will apply sales Tax amount
	 * 
	 * @param salesTaxPercent
	 */
	public void applySalesTax(BigDecimal salesTaxPercent) {
		this.salesTaxPercent = salesTaxPercent;
	}

	/**
	 * Returns the total count of the specific product in a shopping cart, if exist. If not, returns 0.
	 * 
	 * @param productCode
	 * @return
	 */
	public int getUnitCountByProductCode(int productCode) {
		if (!CollectionUtils.isNotEmpty(shoppingItems)) {
			return shoppingItems.stream().filter(i -> i.getProduct().getProductCode() == productCode).map(si -> si.getItemCount()).findFirst().orElse(0);
		}
		return 0;
	}

	/**
	 * Returns the unit price of the specific product in a shopping cart, if exist. If not, returns 0.00.
	 * 
	 * @param productCode
	 * @return
	 */
	public BigDecimal getUnitPriceByProductCode(int productCode) {
		if (!CollectionUtils.isNotEmpty(shoppingItems)) {
			return shoppingItems.stream().filter(i -> i.getProduct().getProductCode() == productCode).map(si -> si.getTotalPricePerItem()).findFirst().orElse(BigDecimal.ZERO);
		}
		return BigDecimal.ZERO;
	}

}
