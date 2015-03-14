package org.beencoder.type.element;

import java.util.List;

/**
 * Created by tityenok on 3/14/15.
 */
public class ListBeeElement implements BeeElement<List<? super BeeElement>>
{
  private List<? super BeeElement> beeElements;

  public ListBeeElement(List<? super BeeElement> beeElements)
  {
    this.beeElements = beeElements;
  }

  @Override
  public List<? super BeeElement> getValue()
  {
    return beeElements;
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
