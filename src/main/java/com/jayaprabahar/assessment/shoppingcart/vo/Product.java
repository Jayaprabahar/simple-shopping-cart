/**
 * 
 */
package com.jayaprabahar.assessment.shoppingcart.vo;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import lombok.Value;

/**
 * <p> Project : shoppingcart </p>
 * <p> Title : Product.java </p>
 * <p> Description: VO class contains information about individual product </p>
 * <p> Created: Mar 25, 2019</p>
 * 
 * @version 1.0
 */
@Value
public class Product {

	private int productCode;
	String productName;
	BigDecimal unitPrice;
	Currency unitCurrency = Currency.getInstance(Locale.getDefault());

}
