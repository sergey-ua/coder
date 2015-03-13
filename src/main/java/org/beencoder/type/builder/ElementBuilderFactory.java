package org.beencoder.type.builder;

import org.beencoder.type.TypeMeta;

import static org.beencoder.type.TypeMeta.*;

/**
 * Created by tityenok on 3/14/15.
 */
public class ElementBuilderFactory
{
  public ElementBuilder createBuilderByType(TypeMeta typeMeta)
  {
    switch (typeMeta)
    {
      case INTEGER:
      {
        return null;
      }
      case STRING:
      {
        return null;
      }
      case LIST:
      {
        return null;
      }
      case DICTIONARY:
      {
        return null;
      }
    }
    return null;
  }
}
