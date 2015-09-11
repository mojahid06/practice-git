package com.orangeandbronze.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
	private final int dollars;
	private final int cents;
	private final Currency currency;

	public Money(Currency currency, int dollars, int cents) {
		if (currency == null){
			throw new IllegalArgumentException("Currency invalid");
		}
	
		this.currency = currency;
		this.dollars = setDollars(dollars, cents);
		this.cents = setCents(dollars, cents);
	}
	
	private int setDollars(int dollars, int cents) {
		while (cents < 0 || cents > 99) {
			if (dollars > 0 && cents < 0 || cents > 99) {
				if (cents > 99) {
					dollars += 1;
					cents -= 100;
				}
				if (cents < 0) {
					dollars -= 1;
					cents += 100;
				}
			} else {
				if (cents > 99) {
					dollars -= 1;
					cents -= 100;
				}
				if (cents < 0) {
					dollars += 1;
					cents += 100;
				}
			}
		}
		return dollars;
	}
	private int setCents(int dollars, int cents) {
		while (cents < 0 || cents > 99) {
			if (dollars > 0 && cents < 0 || cents > 99) {
				if (cents > 99) {
					cents -= 100;
				}
				if (cents < 0) {
					cents += 100;
				}
			} else {
				if (cents > 99) {
					cents -= 100;
				}
				if (cents < 0) {
					cents += 100;
				}
			}
		}
		return cents;
	}
	int neutralize(int dollars, int cents){
	
		return cents;
	}
	
	Money reverseSign(Money money) {
		int dollars = -money.dollars;
		return new Money(money.currency, dollars, money.cents);
	}

	public Money plus(Money money) {
		if (this.currency != money.currency) {
			throw new CurrencyMismatchException("This currency: "
					+ this.currency + "other currency" + money.currency);
		}

		if (this.dollars < 0 && money.dollars < 0) {
			return reverseSign(reverseSign(this).plus(reverseSign(money)));
		} else if (this.dollars > 0 && money.dollars < 0) {
			return this.minus(reverseSign(money));
		} else if (this.dollars < 0 && money.dollars > 0) {
			return reverseSign(reverseSign(this).minus(money));
		}
		int newCents = this.cents + money.cents;
		int newDollars = this.dollars + money.dollars;
		return new Money(currency, newDollars, newCents);
	}

	public Money minus(Money other) {
		if (this.currency != other.currency) {
			throw new CurrencyMismatchException("Trying to add "
					+ this.currency + " to " + other.currency);
		}
		if (this.dollars > 0 && other.dollars > 0) {
			if (this.dollars < other.dollars) {
				return reverseSign(other.minus(this));
			}
			int newCents = this.cents - other.cents;
			int newDollars = this.dollars - other.dollars;
			return new Money(currency, newDollars, newCents);
		}
		return this.plus(reverseSign(other));
	}

	public Money multiply(double i) {
		if (i < 0)
			throw new IllegalOperationException(
					"Connot multiply/divide type Money by negative value");
		double thisDollars = this.dollars;
		double thisCents = this.cents;
		BigDecimal thisMoney = new BigDecimal(thisDollars + thisCents / 100)
				.setScale(2, RoundingMode.HALF_UP);
		BigDecimal multiplicand = new BigDecimal(i);
		BigDecimal newMoney = thisMoney.multiply(multiplicand).setScale(2,
				RoundingMode.HALF_UP);
		int newDollars = newMoney.intValue();
		double newCents = (newMoney.doubleValue() - newDollars) * 100;
		return new Money(currency, newDollars, (int) Math.round(newCents));
	}

	public Money divide(double i) {
		double reciprocal = 1 / i;
		return this.multiply(reciprocal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cents;
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + dollars;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (cents != other.cents)
			return false;
		if (currency != other.currency)
			return false;
		if (dollars != other.dollars)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s %d.%s%d", currency, dollars, cents < 10 ? "0"
				: "", cents);
	}

}
