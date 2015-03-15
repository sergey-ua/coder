package org.beencoder.type.decorator;


import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.IntegerBeeElement;

/**
 * Created by tityenok on 3/15/15.
 */
public class IntegerElementDecorator extends BaseElementDecorator<IntegerBeeElement>
{

  @Override
  protected String formatElement(IntegerBeeElement element)
  {
    return String.format("%s%s%s", TypeMeta.INTEGER.getStartPattern(),
        element.getValue(),
        TypeMeta.INTEGER.getEndPattern());
  }

}
