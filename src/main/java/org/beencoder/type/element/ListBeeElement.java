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
}
