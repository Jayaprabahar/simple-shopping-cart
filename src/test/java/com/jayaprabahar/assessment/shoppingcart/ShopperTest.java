/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jayaprabahar.assessment.shoppingcart.vo.Action;
import com.jayaprabahar.assessment.shoppingcart.vo.Product;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : ShopperTest.java </p>
 * <p> Description: Unit test case class for Shopper </p>
 * <p> Created: Mar 25, 2019</p>
 * 
 * @version 1.0
 */

public class ShopperTest {

	// Single user testing. So one shopperID is used across the testcases
	private int shopperId = -1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shopperId = 170285;
	}

	@Test
	public void shoppingTest_emptyCart() {

		// Given - empty shopping cart
		Shopper shopper = new Shopper(shopperId);

		// When - Nothing added
		// Then
		assertEquals(0, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getTotalPrice());

		assertEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(1, shopper.getShoppingCart().getTotalUnits());
	}

	@Test
	public void shoppingTest_step1() {
		// Given - empty shopping cart
		Shopper shopper = new Shopper(shopperId);
		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.99));

		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(1, shopper.getShoppingCart().getTotalUnits());
		assertNotEquals(5, shopper.getShoppingCart().getTotalUnits());

		// When
		shopper.shopping(doveSoap, 5, Action.ADD);

		// Then
		assertEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertEquals(5, shopper.getShoppingCart().getTotalUnits());

		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(200.00), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(3, shopper.getShoppingCart().getTotalUnits());
	}

	@Test
	public void shoppingTest_allRemoved() {
		// Given - empty shopping cart
		Shopper shopper = new Shopper(shopperId);
		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.99));

		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(1, shopper.getShoppingCart().getTotalUnits());
		assertNotEquals(5, shopper.getShoppingCart().getTotalUnits());

		// When
		shopper.shopping(doveSoap, 5, Action.ADD);
		// When - all removed
		shopper.shopping(doveSoap, 5, Action.REMOVE);

		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(1, shopper.getShoppingCart().getTotalUnits());
		assertNotEquals(5, shopper.getShoppingCart().getTotalUnits());

		assertEquals(BigDecimal.valueOf(0), shopper.getShoppingCart().getTotalPrice());
		assertEquals(0, shopper.getShoppingCart().getTotalUnits());
	}

	@Test
	public void shoppingTest_stepRoundUp() {
		// Given - empty shopping cart
		Shopper shopper = new Shopper(shopperId);
		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.997));

		// When
		shopper.shopping(doveSoap, 7, Action.ADD);

		// Then
		assertEquals(7, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.valueOf(279.98), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(279.979), shopper.getShoppingCart().getTotalPrice());
	}

	@Test
	public void shoppingTest_step2() {
		// Given - empty shopping cart
		Shopper shopper = new Shopper(shopperId);
		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.99));

		assertEquals(0, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());

		// When
		shopper.shopping(doveSoap, 5, Action.ADD);
		// Then
		assertEquals(5, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());

		assertNotEquals(1, shopper.getShoppingCart().getTotalUnits());
		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());

		assertNotEquals(BigDecimal.ZERO, shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.ZERO, shopper.getShoppingCart().getTotalPrice());

		// When - Add additional products of the same type to the shopping cart.
		shopper.shopping(doveSoap, 3, Action.ADD);

		// Then
		assertEquals(8, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.valueOf(319.92), shopper.getShoppingCart().getTotalPrice());

		assertNotEquals(5, shopper.getShoppingCart().getTotalUnits());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());
	}

	@Test
	public void shoppingTest_step3() {
		// Given - empty shopping cart & a product
		Shopper shopper = new Shopper(shopperId);

		Product doveSoap = new Product(101, "Dove Bathing Soap", BigDecimal.valueOf(39.99));
		Product axeDeoSoap = new Product(102, "Axe Deo Soap", BigDecimal.valueOf(99.99));

		assertEquals(0, shopper.getShoppingCart().getTotalUnits());
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getTotalPrice());
		assertNotEquals(BigDecimal.valueOf(199.95), shopper.getShoppingCart().getTotalPrice());

		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getUnitPriceByProductCode(101));
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getUnitPriceByProductCode(102));
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getUnitPriceByProductCode(103));

		assertEquals(0, shopper.getShoppingCart().getUnitCountByProductCode(101));
		assertEquals(0, shopper.getShoppingCart().getUnitCountByProductCode(102));
		assertEquals(0, shopper.getShoppingCart().getUnitCountByProductCode(103));

		shopper.getShoppingCart().applySalesTax(BigDecimal.valueOf(12.5));

		assertEquals(BigDecimal.valueOf(12.5), shopper.getShoppingCart().getSalesTaxPercent());
		assertNotEquals(BigDecimal.ZERO, shopper.getShoppingCart().getSalesTaxPercent());
		assertNotEquals(BigDecimal.valueOf(12.50).setScale(2), shopper.getShoppingCart().getSalesTaxPercent());

		// When
		shopper.shopping(doveSoap, 2, Action.ADD);
		shopper.shopping(axeDeoSoap, 2, Action.ADD);

		// Then
		assertEquals(4, shopper.getShoppingCart().getTotalUnits());
		assertEquals(2, shopper.getShoppingCart().getUnitCountByProductCode(101));
		assertEquals(2, shopper.getShoppingCart().getUnitCountByProductCode(102));
		assertEquals(BigDecimal.valueOf(39.99), shopper.getShoppingCart().getUnitPriceByProductCode(101));
		assertEquals(BigDecimal.valueOf(99.99), shopper.getShoppingCart().getUnitPriceByProductCode(102));
		assertEquals(BigDecimal.valueOf(35.00).setScale(2), shopper.getShoppingCart().getSalesTaxAmount());
		assertEquals(BigDecimal.valueOf(314.96), shopper.getShoppingCart().getTotalPrice());

		assertNotEquals(7, shopper.getShoppingCart().getTotalUnits());
		// Product with code 103 not exist
		assertEquals(0, shopper.getShoppingCart().getUnitCountByProductCode(103));
		assertNotEquals(2, shopper.getShoppingCart().getUnitCountByProductCode(103));

		// Product with code 105 not exist
		assertEquals(BigDecimal.ZERO, shopper.getShoppingCart().getUnitPriceByProductCode(105));
		assertNotEquals(BigDecimal.valueOf(39), shopper.getShoppingCart().getUnitPriceByProductCode(101));

	}
}
