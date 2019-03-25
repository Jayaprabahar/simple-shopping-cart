/**
 * 
 */
package com.jayaprabahar.prep.shoppingcart;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jayaprabahar.prep.shoppingcart.vo.Action;
import com.jayaprabahar.prep.shoppingcart.vo.Product;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : ShopperTest.java </p>
 * <p> Description: </p>
 * <p> Created: Mar 25, 2019</p>
 * <p> Company: AIRFRANCE-KLM </p>
 * 
 * @version 1.0
 */

public class ShopperTest {

	// Single user testing. So one shopperID is used across the testcases
	int shopperId = -1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shopperId = 170285;
	}

	@Test
	public void shoppingTest() {
		// Given - empty shopping cart
		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.99));
		Shopper shopper = new Shopper(shopperId);
		
		assertEquals(BigDecimal.valueOf(0), shopper.getShoppingCart().getTotalPrice());
		assertEquals(0, shopper.getShoppingCart().getTotalUnits());

		// When
		shopper.shopping(doveSoap, 5, Action.ADD);

		// Then
		assertEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertEquals(5, shopper.getShoppingCart().getTotalUnits());
		
		// When
		shopper.shopping(doveSoap, 5, Action.REMOVE);
		
		// Then
		assertEquals(BigDecimal.valueOf(0), shopper.getShoppingCart().getTotalPrice());
		assertEquals(0, shopper.getShoppingCart().getTotalUnits());

	}

}
