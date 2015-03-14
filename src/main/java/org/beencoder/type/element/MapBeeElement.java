package org.beencoder.type.element;

import java.util.Map;

/**
 * Created by tityenok on 3/14/15.
 */
public class MapBeeElement implements BeeElement<Map<StringBeeElement, ? super BeeElement>>
{
  private Map<StringBeeElement, ? super BeeElement> map;

  public MapBeeElement(Map<StringBeeElement, ? super BeeElement> map)
  {
    this.map = map;
  }
  @Override
  public Map<StringBeeElement, ? super BeeElement> getValue()
  {
    return map;
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

    MapBeeElement that = (MapBeeElement) o;

    if (map != null ? !map.equals(that.map) : that.map != null)
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    return map != null ? map.hashCode() : 0;
  }
}
