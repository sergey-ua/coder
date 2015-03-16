package org.beencoder.type.builder;

import org.beencoder.excpetion.InvalidValueException;
import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.BeeElement;
import org.beencoder.type.element.MapBeeElement;
import org.beencoder.type.element.StringBeeElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tityenok on 3/14/15.
 */
public class MapElementBuilder extends ContainerElementBuilder<MapBeeElement>
{
  private final Map<StringBeeElement,  BeeElement> map = new HashMap<>();
  private StringBeeElement currentKey;


  @Override
  public void appendElement(BeeElement element) throws InvalidValueException
  {
    if (currentKey == null)
    {
      setKey(element);
    } else
    {
      setValue(element);
    }
  }

  private void setValue(BeeElement element)
  {
    this.map.put(new StringBeeElement(currentKey.getValue()), element);
    currentKey = null;
  }

  private void setKey(BeeElement key) throws InvalidValueException
  {
    if (key instanceof StringBeeElement)
    {
      this.currentKey = (StringBeeElement) key;
    }
    else
    {
      throw new InvalidValueException("DICTIONARY keys must be of type STRING only");
    }
  }


  @Override
  public MapBeeElement build()
  {
    return new MapBeeElement(map);
  }

  @Override
  public boolean canBeCompletedWith(char token)
  {
    return isAllSet() && TypeMeta.DICTIONARY.getEndPattern().equals(token);
  }

  private boolean isAllSet()
  {
    return currentKey==null;
  }

  @Override
  public TypeMeta getSupportedTypeMeta()
  {
    return TypeMeta.DICTIONARY;
  }

}
