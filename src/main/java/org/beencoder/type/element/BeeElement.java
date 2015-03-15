package org.beencoder.type.element;

import org.beencoder.type.TypeMeta;

/**
 * Created by tityenok on 3/14/15.
 */
public interface BeeElement<T>
{
  T getValue();

  TypeMeta getType();
}
