package org.beencoder.type.element;

/**
 * Created by tityenok on 3/14/15.
 */
public class StringBeeElement implements BeeElement<String>
{

  private String value;

  public StringBeeElement(String value)
  {
    this.value = value;
  }

  @Override
  public String getValue()
  {
    return value;
  }

  public static StringBeeElement fromString(String value)
  {
    return new StringBeeElement(value);
  }
}
