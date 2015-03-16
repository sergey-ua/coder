package org.beencoder.type.decorator;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.BeeElement;
import org.beencoder.type.element.ListBeeElement;

import java.util.List;

/**
 * Created by tityenok on 3/15/15.
 */
public class ListElementDecorator extends BaseElementDecorator<ListBeeElement>
{
  private final ElementDecoratorFactory decoratorFactory;

  public ListElementDecorator(ElementDecoratorFactory decoratorFactory)
  {
    this.decoratorFactory = decoratorFactory;
  }

  @Override
  protected String formatElement(ListBeeElement element)
  {
    List<? extends BeeElement> values = element.getValue();
    StringBuilder formattedValue = new StringBuilder();
    formattedValue.append(TypeMeta.LIST.getStartPattern());
    for (BeeElement value : values)
    {
      if (value != null)
      {
        ElementDecorator decorator = decoratorFactory.getOrCreateDecoratorForElement(value.getType());
        if (decorator == null)
        {
          throw new IllegalStateException("Cannot find decorator for type " + value.getType());
        }
        formattedValue.append(decorator.toPlainString(value));
      }
    }
    formattedValue.append(TypeMeta.LIST.getEndPattern());
    return formattedValue.toString();
  }
}
