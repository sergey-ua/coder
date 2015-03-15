package org.beencoder.type.decorator;

import org.beencoder.type.element.BeeElement;
import org.beencoder.type.element.IntegerBeeElement;

/**
 * Created by tityenok on 3/15/15.
 */
public abstract class BaseElementDecorator<T extends BeeElement> implements ElementDecorator<T>
{

  public static final String DEFAULT_NULL_VALUE = "";

  @Override
  public String toPlainString(T element)
  {
    if (nullOrWithoutValue(element))
    {
      return nullValue();
    }
    return formatElement(element);
  }

  private boolean nullOrWithoutValue(T element)
  {
    return (element == null || element.getValue() == null);
  }

  protected abstract String formatElement(T element);

  protected String nullValue()
  {
    return DEFAULT_NULL_VALUE;
  };


}
