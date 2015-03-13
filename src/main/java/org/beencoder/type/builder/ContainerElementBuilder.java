package org.beencoder.type.builder;

import org.beencoder.type.element.BeeElement;

/**
 * Defines base interface for builders of types which can contain other b-types (List, Map)
 *
 * @see org.beencoder.type.TypeMeta
 *
 * @author tityenok
 */
public interface ContainerElementBuilder<T extends BeeElement> extends ElementBuilder<T>
{
  void appendElement(BeeElement element);
}
