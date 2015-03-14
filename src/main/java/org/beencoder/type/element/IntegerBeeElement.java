package org.beencoder.type.element;

/**
 * Created by tityenok on 3/14/15.
 */
public class IntegerBeeElement implements BeeElement<Integer>
{
  private Integer value;

  public IntegerBeeElement(int value)
  {
    this.value = value;
  }

  @Override
  public Integer getValue()
  {
    return value;
  }

  public static IntegerBeeElement fromInt(int value)
  {
    return new IntegerBeeElement(value);
  }
}
