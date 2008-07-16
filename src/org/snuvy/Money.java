package org.snuvy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Money {
  private final BigDecimal _amount;
  
  public Money(final String amount) throws ParseException {
    NumberFormat df = DecimalFormat.getInstance();
    _amount = BigDecimal.valueOf(df.parse(amount).doubleValue());    
  }
  public Money(final BigDecimal amount) {
    _amount = amount;
  }
  public Money(final long dollars, final int cents) {
    _amount = BigDecimal.valueOf(dollars*100+cents);
  }
  public Money(double d) {
    _amount = BigDecimal.valueOf(d);
  }
  public Money() {
    _amount = new BigDecimal(0l);
  }
  public String format() {
    return format(_amount);
  }
  public static String format(final BigDecimal amount) {
    NumberFormat dollarformat = NumberFormat.getCurrencyInstance();
    dollarformat.setGroupingUsed(true);
    return dollarformat.format(amount.doubleValue());
  }
  public static int cents(final BigDecimal amount) {
    int cents = amount.divideAndRemainder(BigDecimal.valueOf(100))[1].intValue();
    return cents;
  }
  
  public static long dollars(final BigDecimal amount) {
    long dollars = amount.longValue();
    return dollars;
  }
  public int cents() {
    return cents(_amount);
  }
  public long dollars() {
    return dollars(_amount);
  }
  public BigDecimal bd$() {
    return _amount;
  }
  public static Money m$(final String amount) throws ParseException {
    NumberFormat df = DecimalFormat.getInstance();
    BigDecimal decimal = BigDecimal.valueOf(df.parse(amount).doubleValue());
    return new Money(decimal);
  }
  public static Money m$(final long dollars, final int cents) {
    return new Money( BigDecimal.valueOf(dollars+ (cents/100) ) );
  }
  public Money add(Money money) {
    return new Money(bd$().add(money.bd$()));
  }
  public Money subtract(Money money) {
    return new Money(bd$().subtract(money.bd$()));
  }
  public Money divide(Money money) {
    return new Money(bd$().divide(money.bd$()));
  }
  public Money multiply(Money money) {
    return new Money(bd$().multiply(money.bd$()));
  }
  public Money divide(long n) {
    BigDecimal dividend = bd$();
    BigDecimal divisor = BigDecimal.valueOf(n);
    return new Money( dividend.divide(divisor, new MathContext(100, RoundingMode.HALF_UP) ));
  }
  public Money multiply(long n) {
    BigDecimal result = bd$().multiply(BigDecimal.valueOf(n) , new MathContext(100, RoundingMode.HALF_UP) );
    return new Money( result);
  }
  public String toString() {
    return format();
  }
  public Money negate() {
    return new Money(bd$().negate());
  }
}
