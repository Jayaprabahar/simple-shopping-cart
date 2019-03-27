/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart.vo;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : ShoppingCart.java </p>
 * <p> Description: ShoppingCart contains the individual shoppers(based on shopperId) shopping cart and total price of the products</p>
 * <p> Created: Mar 24, 2019</p>
 * 
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class ShoppingCart {

	Map<Product, Integer> cartedProducts;
	int totalUnits;
	BigDecimal totalPrice;
	BigDecimal salesTaxPercent;
	BigDecimal salesTaxAmount;

	/**
	 * Update the total price and total count every time when it is called
	 */
	public void updateTotals() {
		setTotalPrice(BigDecimal.ZERO);
		setTotalUnits(0);

		if (MapUtils.isNotEmpty(cartedProducts)) {
			cartedProducts.forEach((k, v) -> {
				totalPrice = totalPrice.add(k.getUnitPrice().multiply(new BigDecimal(v))).setScale(2, BigDecimal.ROUND_HALF_UP);
				totalUnits += v;
			});
			salesTaxAmount = totalPrice.multiply(salesTaxPercent).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalPrice = totalPrice.add(salesTaxAmount);
		}
	}

	/**
	 * Returns the total count of the specific product in a shopping cart, if exist. If not, returns 0.
	 * 
	 * @param productCode
	 * @return
	 */
	public int getUnitCountByProductCode(int productCode) {
		if (MapUtils.isNotEmpty(cartedProducts)) {
			return cartedProducts.entrySet().stream().filter(k -> (k.getKey().getProductCode() == productCode)).map(key -> key.getValue()).findFirst().orElse(0);
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
		if (MapUtils.isNotEmpty(cartedProducts)) {
			return cartedProducts.entrySet().stream().filter(k -> (k.getKey().getProductCode() == productCode)).map(key -> key.getKey().getUnitPrice()).findFirst().orElse(BigDecimal.ZERO);
		}
		return BigDecimal.ZERO;
	}

}
