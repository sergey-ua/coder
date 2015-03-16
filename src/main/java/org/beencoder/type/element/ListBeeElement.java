package org.beencoder.type.element;

import org.beencoder.type.TypeMeta;

import java.util.List;

/**
 * Created by tityenok on 3/14/15.
 */
public class ListBeeElement implements BeeElement<List<? extends BeeElement>>
{
  private final List<? extends BeeElement> beeElements;

  public ListBeeElement(List<? extends BeeElement> beeElements)
  {
    this.beeElements = beeElements;
  }

  @Override
  public List<? extends BeeElement> getValue()
  {
    return beeElements;
  }

  @Override
  public TypeMeta getType()
  {
    return TypeMeta.LIST;
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

    ListBeeElement that = (ListBeeElement) o;

    if (beeElements != null ? !beeElements.equals(that.beeElements) : that.beeElements != null)
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    return beeElements != null ? beeElements.hashCode() : 0;
  }
}
