package org.beencoder.type.builder;

import org.beencoder.type.element.BeeElement;

/**
 * Defines base interface for builders of simple types such as String and Integer
 *
 * Call {@code addToken(c)} only after {@code isTokenApplicable} returns {@code True}
 *
 * @see org.beencoder.type.TypeMeta
 *
 * @author tityenok
 */
public interface ValueElementBuilder<T extends BeeElement> extends ElementBuilder<T>
{
  boolean isTokenApplicable(char token);

  void addToken(char token);

}
