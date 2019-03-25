/**
 * 
 */
package com.jayaprabahar.prep.shoppingcart.vo;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : ShoppingCart.java </p>
 * <p> Description: ShoppingCart contains the shopping cart and total price of the products</p>
 * <p> Created: Mar 24, 2019</p>
 * 
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class ShoppingCart {

	Map<Product, Integer> cartedProducts;
	BigDecimal totalPrice;
	int totalUnits;

	/**
	 * Update the total price every time when it is called
	 */
	public void updateTotals() {
		if (MapUtils.isEmpty(cartedProducts)) {
			setTotalPrice(BigDecimal.ZERO);
			setTotalUnits(0);
		} else {
			cartedProducts.forEach((k, v) -> {
				totalPrice = totalPrice.add(k.getUnitPrice().multiply(new BigDecimal(v)));
				totalUnits += v;
			});
		}
	}

}
