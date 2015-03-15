package org.beencoder.type.decorator;

import org.beencoder.type.element.BeeElement;

/**
 * Converts {@link org.beencoder.type.element.BeeElement} to plain string
 *
 * MUST BE STATELESS!
 */
public interface ElementDecorator<T extends BeeElement>
{
  String toPlainString(T element);
}
