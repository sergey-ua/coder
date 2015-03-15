package org.beencoder.type.decorator;

import org.beencoder.type.element.StringBeeElement;

/**
 * Created by tityenok on 3/15/15.
 */
public class StringElementDecorator extends BaseElementDecorator<StringBeeElement>
{
  @Override
  protected String formatElement(StringBeeElement element)
  {
    String value = element.getValue();
    return String.valueOf(value.length()) + ":" + value;

  }
}
