package org.beencoder.type.decorator;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.BeeElement;
import org.beencoder.type.element.MapBeeElement;
import org.beencoder.type.element.StringBeeElement;

import java.util.*;

/**
 * Created by tityenok on 3/15/15.
 */
public class MapElementDecorator extends BaseElementDecorator<MapBeeElement>
{

  private final ElementDecoratorFactory elementDecoratorFactory;

  public MapElementDecorator(ElementDecoratorFactory elementDecoratorFactory)
  {
    this.elementDecoratorFactory = elementDecoratorFactory;
  }

  @Override
  protected String formatElement(MapBeeElement element)
  {
    Map<StringBeeElement, ? extends BeeElement> mapValue = element.getValue();
    StringBeeElement[] sortedKeys = sortKeys(mapValue.keySet());
    StringBuilder formattedResult = new StringBuilder();
    formattedResult.append(TypeMeta.DICTIONARY.getStartPattern());
    for (StringBeeElement key : sortedKeys)
    {
      formatKey(formattedResult, key);
      formatValue(formattedResult, mapValue.get(key));
    }
    formattedResult.append(TypeMeta.DICTIONARY.getEndPattern());
    return formattedResult.toString();

  }

  private StringBeeElement[] sortKeys(Set<StringBeeElement> stringBeeElements)
  {
    StringBeeElement[] stringArray = stringBeeElements.toArray(new StringBeeElement[stringBeeElements.size()]);
    Arrays.sort(stringArray);
    return stringArray;
  }

  private void formatKey(StringBuilder formattedResult, StringBeeElement key)
  {
    formattedResult.append(elementDecoratorFactory.getOrCreateDecoratorForElement(key.getType()).toPlainString(key));
  }

  private void formatValue(StringBuilder formattedResult, BeeElement element)
  {
    if (element != null)
    {
      ElementDecorator elementDecorator = elementDecoratorFactory
          .getOrCreateDecoratorForElement(element.getType());
      if (elementDecorator == null)
      {
        throw new IllegalStateException("Cannot find decorator for type " + element.getType());
      }
      formattedResult.append(elementDecorator.toPlainString(element));
    }

  }

}
