package org.beencoder.type.builder;

import org.beencoder.type.element.BeeElement;

/**
 * Defines base interface for builders of types which can contain other b-types (List, Map)
 *
 * @see org.beencoder.type.TypeMeta
 *
 * @author tityenok
 */
public abstract class ContainerElementBuilder<T extends BeeElement> implements ElementBuilder<T>
{
  public abstract void appendElement(BeeElement element);

  @Override
  public boolean canContainElements()
  {
    return true;
  }
}
