package org.snuvy;

// http://www.f4.fhtw-berlin.de/~weberwu/info2/labs/Ex2.shtml
public class Ratio{
// For creating an object that can store a rational number.
// A rational number can be expressed as a fraction 
// numerator / denominator

  protected int numerator;
  protected int denominator;

  public Ratio (int top, int bottom)
  // pre:  bottom != 0
  // post: constructs a ratio equivalent to top / bottom
  {
     numerator = top;
     denominator = bottom;
  }

  public int getNumerator()
  // post: returns the numerator of the fraction
  {
     return numerator;
  }

  public int getDenominator()
  // post: returns the denominator of the fraction
  {
     return denominator;
  }

  public double value()
  // post: returns the value of the fraction as a real number
  {
     return (double) numerator / (double) denominator;
  }
}