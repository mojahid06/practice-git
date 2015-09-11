package com.orangeandbronze.money;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTest {

	
	@Test
	public void createNewMoneyObjectWithCentGreaterThan100(){
		Money money = new Money(Currency.USD, 5, 225);
		Money expected = new Money(Currency.USD, 7, 25);
		assertEquals(expected, money);
	}
	@Test
	public void createNewMoneyObjectWithCentLesserThan0(){
		Money money = new Money(Currency.USD, 5, -125);
		Money expected = new Money(Currency.USD, 3, 75);
		assertEquals(expected, money);
	}
	@Test
	public void createNewNegativeMoneyObjectWithCentGreaterThan100(){
		Money money = new Money(Currency.USD, -5, 125);
		Money expected = new Money(Currency.USD, -4, 25);
		assertEquals(expected, money);
	}
	@Test
	public void createNewNegativeMoneyObjectWithCentLesserThan0(){
		Money money = new Money(Currency.USD, -5, -25);
		Money expected = new Money(Currency.USD, -4, 75);
		assertEquals(expected, money);
	}
	
	
		
	@Test
	public void makeNegativeNumberAPositiveNumber() {
		Money negative = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, 5, 50);
		Money actual = negative.reverseSign(negative);
		assertEquals(expected, actual);
	}
	
	
	
	@Test
	public void addTwoPositiveWholeNumbersSameCurrency() {
		Money money1 = new Money(Currency.USD, 10, 0);
		Money money2 = new Money(Currency.USD, 5, 0);
		Money expected = new Money(Currency.USD, 15, 0);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test(expected = CurrencyMismatchException.class)
	public void addTwoPositiveWholeNumbersDifferentCurrency() {
		Money money1 = new Money(Currency.USD, 10, 00);
		Money money2 = new Money(Currency.PHP, 5, 00);
		money1.plus(money2);
	}
	@Test
	public void positiveDecimalPlusPositiveDecimal() {
		Money money1 = new Money(Currency.USD, 10, 50);
		Money money2 = new Money(Currency.USD, 5, 55); //NOTE
		Money expected = new Money(Currency.USD, 16, 05);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeDecimalWithLargerCentsPlusNegativeDecimal(){
		Money money1 = new Money(Currency.USD, -10, 75);
		Money money2 = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, -16, 25);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeDecimalWithSmallerCentsPlusNegativeDecimal(){
		Money money1 = new Money(Currency.USD, -10, 50);
		Money money2 = new Money(Currency.USD, -5, 75);
		Money expected = new Money(Currency.USD, -16, 25);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveDecimalWithLargerCentsPlusNegativeDecimal(){
		Money money1 = new Money(Currency.USD, 10, 75);
		Money money2 = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, 5, 25);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveDecimalWithSmallerCentsPlusNegativeDecimal(){
		Money money1 = new Money(Currency.USD, 10, 25);
		Money money2 = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, 4, 75);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeDecimalWithLargerCentsPlusPositiveDecimal(){
		Money money1 = new Money(Currency.USD, -10, 75);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, -5, 25);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeDecimalWithSmallerCentsPlusPositiveDecimal(){
		Money money1 = new Money(Currency.USD, -10, 25);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, -4, 75);
		Money actual = money1.plus(money2);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void subtractTwoPositiveWholeNumbersSameCurrency() {
		Money money1 = new Money(Currency.USD, 10, 0);
		Money money2 = new Money(Currency.USD, 5, 0);
		Money expected = new Money(Currency.USD, 5, 0);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test(expected = CurrencyMismatchException.class)
	public void subtractTwoPositiveWholeNumbersDifferentCurrency() {
		Money money1 = new Money(Currency.USD, 10, 0);
		Money money2 = new Money(Currency.PHP, 5, 0);
		Money expected = new Money(Currency.USD, 5, 0);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveBiggerDecimalWithLargerCentsMinusPositiveSmallerDecimal() {
		Money money1 = new Money(Currency.USD, 10, 75);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, 5, 25);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveBiggerDecimalWithSmallerCentsMinusPositiveSmallerDecimal() {
		Money money1 = new Money(Currency.USD, 10, 25);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, 4, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveSmallerDecimalWithLargerCentsMinusPositiveBiggerDecimal() {
		Money money1 = new Money(Currency.USD, 5, 75);
		Money money2 = new Money(Currency.USD, 10, 50);
		Money expected = new Money(Currency.USD, -4, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveSmallerDecimalWithSmallerCentsMinusPositiveBiggerDecimal() {
		Money money1 = new Money(Currency.USD, 5, 5);
		Money money2 = new Money(Currency.USD, 10, 50);
		Money expected = new Money(Currency.USD, -5, 45);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveBiggerDecimalWithLargerCentsMinusNegativeSmallerDecimal(){
		Money money1 = new Money(Currency.USD, 10, 75);
		Money money2 = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, 16, 25);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveBiggerDecimalWithSmallerCentsMinusNegativeSmallerDecimal(){
		Money money1 = new Money(Currency.USD, 10, 25);
		Money money2 = new Money(Currency.USD, -5, 50);
		Money expected = new Money(Currency.USD, 15, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveSmallerDecimalWithLargerCentsMinusNegativeBiggerDecimal(){
		Money money1 = new Money(Currency.USD, 5, 75);
		Money money2 = new Money(Currency.USD, -10, 50);
		Money expected = new Money(Currency.USD, 16, 25);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveSmallerDecimalWithSmallerCentsMinusNegativeBiggerDecimal(){
		Money money1 = new Money(Currency.USD, 5, 25);
		Money money2 = new Money(Currency.USD, -10, 50);
		Money expected = new Money(Currency.USD, 15, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeBiggerDecimalWithLargerCentsMinusPositiveSmallerDecimal(){
		Money money1 = new Money(Currency.USD, -10, 75);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, -16, 25);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeBiggerDecimalWithSmallerCentsMinusPositiveSmallerDecimal(){
		Money money1 = new Money(Currency.USD, -10, 25);
		Money money2 = new Money(Currency.USD, 5, 50);
		Money expected = new Money(Currency.USD, -15, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeSmallerDecimalMinusPositiveBiggerDecimal(){
		Money money1 = new Money(Currency.USD, -5, 25);
		Money money2 = new Money(Currency.USD, 10, 50);
		Money expected = new Money(Currency.USD, -15, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	@Test
	public void negativeDecimaMinusNegativeDecimal(){
		Money money1 = new Money(Currency.USD, -5, 75);
		Money money2 = new Money(Currency.USD, -10, 50);
		Money expected = new Money(Currency.USD, 4, 75);
		Money actual = money1.minus(money2);
		assertEquals(expected, actual);
	}
	
	
	
	@Test
	public void moneyTimesInt(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.multiply(5);
		Money expected = new Money(Currency.USD, 52, 50);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyTimesExactDouble(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.multiply(5.00);
		Money expected = new Money(Currency.USD, 52, 50);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyTimesInexactDouble(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.multiply(5.44);
		Money expected = new Money(Currency.USD, 57, 12);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyTimesInexactDoubleSmallerThanOne(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.multiply(.30);
		Money expected = new Money(Currency.USD, 3, 15);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyTimesInexactDoubleSmallerThanOneOtherValue(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.multiply(.798);
		Money expected = new Money(Currency.USD, 8, 38);
		assertEquals(expected, actual);
	}
	@Test(expected = IllegalOperationException.class)
	public void moneyTimesNegativeValue(){
		Money money = new Money(Currency.USD, 10, 50);
		money.multiply(-5);
	}
	
	
	
	@Test
	public void moneyDivideInt(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.divide(5);
		Money expected = new Money(Currency.USD, 2, 10);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyDivideDouble(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.divide(5.5);
		Money expected = new Money(Currency.USD, 1, 91);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyDivideDoubleSmallerThanOne(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.divide(.5);
		Money expected = new Money(Currency.USD, 21, 00);
		assertEquals(expected, actual);
	}
	@Test
	public void moneyDivideDoubleSmallerThanOneOtherValue(){
		Money money = new Money(Currency.USD, 10, 50);
		Money actual = money.divide(.33);
		Money expected = new Money(Currency.USD, 31, 82);
		assertEquals(expected, actual);
	}
	@Test(expected = IllegalOperationException.class)
	public void moneyDividedByNegativeValue(){
		Money money = new Money(Currency.USD, 10, 50);
		money.divide(-5);
	}
	
}
