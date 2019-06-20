/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jayaprabahar.assessment.shoppingcart.vo.Action;
import com.jayaprabahar.assessment.shoppingcart.vo.Product;
import com.jayaprabahar.assessment.shoppingcart.vo.ShoppingCart;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : Shopping.java </p>
 * <p> Description: Shopper does shopping. Simulates a Session of shopping person </p>
 * <p> Created: Mar 25, 2019</p>
 * 
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class Shopper {

	private int shopperId;
	private ShoppingCart shoppingCart;

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
		return new ShoppingCart(new ArrayList<>(), 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
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

		return shoppingCart.updateShoppingCart(product, unitCount, shopperAction);
	}

}
