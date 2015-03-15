package org.beencoder.type.decorator;

import org.beencoder.type.element.BeeElement;

/**
 * Converts {@link org.beencoder.type.element.BeeElement} to plain string
 *
 *
 */
public interface ElementDecorator<T extends BeeElement>
{
  /**
   * Formats type to its string representation.
   * MUST NOT ADD OR CHANGE STATE OF THIS OBJECT!
   *
   * @param element - BeeElement to format
   * @return formatted string
   */
  String toPlainString(T element);
}
