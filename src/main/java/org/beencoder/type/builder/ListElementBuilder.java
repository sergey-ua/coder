package org.beencoder.type.builder;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.BeeElement;
import org.beencoder.type.element.ListBeeElement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tityenok on 3/14/15.
 */
public class ListElementBuilder extends ContainerElementBuilder<ListBeeElement>
{
  private final List<BeeElement> elements = new LinkedList<>();

  @Override
  public void appendElement(BeeElement element)
  {
    elements.add(element);
  }

  @Override
  public ListBeeElement build()
  {
    return new ListBeeElement(elements);
  }

  @Override
  public boolean canBeCompletedWith(char token)
  {
    return TypeMeta.LIST.getEndPattern().equals(token);
  }

  @Override
  public TypeMeta getSupportedTypeMeta()
  {
    return TypeMeta.LIST;
  }
}
