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
public abstract class ValueElementBuilder<T extends BeeElement> implements ElementBuilder<T>
{
  public abstract  boolean isTokenApplicable(char token);

  public abstract void addToken(char token);

  @Override
  public boolean canContainElements()
  {
    return false;
  }
}
