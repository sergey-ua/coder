package org.beencoder.type.builder;

import org.beencoder.type.element.BeeElement;

/**
 * Constructs {@link org.beencoder.type.element.BeeElement}
 *
 * Call {@code build()} only after {@code canBeCompleted(c) } returns {@code true}
 * @author tityenok
 */
public interface ElementBuilder<T extends BeeElement>
{
  T build();

  boolean canBeCompletedWith(char token);
}
