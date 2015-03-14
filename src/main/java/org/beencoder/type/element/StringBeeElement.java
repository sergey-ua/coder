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

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    StringBeeElement that = (StringBeeElement) o;

    if (value != null ? !value.equals(that.value) : that.value != null)
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    return value != null ? value.hashCode() : 0;
  }
}
