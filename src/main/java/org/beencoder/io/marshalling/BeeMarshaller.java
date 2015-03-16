package org.beencoder.io.marshalling;

import org.beencoder.type.decorator.ElementDecorator;
import org.beencoder.type.decorator.ElementDecoratorFactory;
import org.beencoder.type.element.BeeElement;

/**
 * Created by tityenok on 3/15/15.
 */
public class BeeMarshaller
{
  private final ElementDecoratorFactory decoratorFactory = new ElementDecoratorFactory();

  public byte[] marshall(BeeElement element)
  {
    assert element != null : "Cannot marshall null type";

    ElementDecorator decorator = decoratorFactory.getOrCreateDecoratorForElement(element.getType());
    if (decorator == null)
    {
      throw new IllegalArgumentException("Cannot marshall element of type " + element.getType());
    }
    return decorator.toPlainString(element).getBytes();
  }
}
