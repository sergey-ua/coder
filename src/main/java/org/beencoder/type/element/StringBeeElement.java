package org.beencoder.type.element;

import org.beencoder.type.TypeMeta;

/**
 * Created by tityenok on 3/14/15.
 */
public class StringBeeElement implements BeeElement<String>, Comparable<StringBeeElement>
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

  @Override
  public TypeMeta getType()
  {
    return TypeMeta.STRING;
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

  @Override
  public int compareTo(StringBeeElement o)
  {
    if (o == null)
    {
      return 1;
    }
    if (this == o)
    {
      return 0;
    }
    if (o.getValue() == null)
    {
      return value == null ? 0 : 1;
    }
    if (this.value == null)
    {
      return o.getValue() == null ? 0 : -1;
    }
    return this.value.compareTo(o.getValue());
  }
}
