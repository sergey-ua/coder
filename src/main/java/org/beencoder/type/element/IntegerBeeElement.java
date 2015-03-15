package org.beencoder.type.element;

import org.beencoder.type.TypeMeta;

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

  @Override
  public TypeMeta getType()
  {
    return TypeMeta.INTEGER;
  }

  public static IntegerBeeElement fromInt(int value)
  {
    return new IntegerBeeElement(value);
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

    IntegerBeeElement that = (IntegerBeeElement) o;

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
