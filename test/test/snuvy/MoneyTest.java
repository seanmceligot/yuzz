package test.snuvy;

import org.snuvy.Money;

import java.text.ParseException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MoneyTest extends TestCase {
  public <T> void eq(T expected, T actual) {
    System.out.println(actual);
    Assert.assertEquals(expected,actual);
  }
    public void testCurrency() throws ParseException {
      System.out.println(new Money("99.99").format());
      System.out.println(new Money(99l,99).format());
      Money total = new Money(0, 0);
      total = total.add(new Money("99.99"));
      eq("$99.99", total.format());
      
      total = total.add(new Money("0.01"));
      eq("$100.00", total.format());

      total = total.subtract(new Money("0.01"));
      eq("$99.99", total.format());

      total = total.add(new Money("10,000"));
      eq("$10,099.99", total.format());

      
      eq(10099l, total.dollars());
      eq(99, total.cents());
    }
}
