/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart;

import java.math.BigDecimal;
import java.util.HashMap;

import com.jayaprabahar.assessment.shoppingcart.vo.Action;
import com.jayaprabahar.assessment.shoppingcart.vo.Product;
import com.jayaprabahar.assessment.shoppingcart.vo.ShoppingCart;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : Shopping.java </p>
 * <p> Description: Shopper does shopping. Simulates a Session of shopping person </p>
 * <p> Created: Mar 25, 2019</p>
 * 
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Shopper {

	int shopperId;
	ShoppingCart shoppingCart;

	/**
	 * Create a shopper instance based on shopper id.
	 * 
	 * @param shopperId
	 */
	public Shopper(int shopperId) {
		this.shopperId = shopperId;
		this.shoppingCart = getEmptyShoppingCart();
	}

	/**
	 * Returns an empty shopping cart for the shopper with this shopperId
	 */
	private ShoppingCart getEmptyShoppingCart() {
		return new ShoppingCart(new HashMap<>(), 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
	}

	/**
	 * Shopper object performs shopping action 
	 * 
	 * @param shoppingCart
	 * @param product
	 * @param unitCount
	 * @param shopperAction
	 * @return updatedShoppingCart
	 */
	ShoppingCart shopping(Product product, int unitCount, Action shopperAction) {
		if (shoppingCart == null)
			shoppingCart = getEmptyShoppingCart();

		int updatedUnitCount = shoppingCart.getCartedProducts().getOrDefault(product, 0) + unitCount;
		shoppingCart.getCartedProducts().put(product, Math.max(0, updatedUnitCount * (shopperAction.equals(Action.ADD) ? 1 : -1)));

		// Remove products with 0 units. This happens when the users add and removes the same number of
		// units for a specific product
		shoppingCart.getCartedProducts().entrySet().removeIf(k -> k.getValue() == 0);
		shoppingCart.updateTotals();

		return shoppingCart;
	}

}
