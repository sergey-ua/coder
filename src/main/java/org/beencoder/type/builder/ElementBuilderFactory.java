package org.beencoder.type.builder;

import org.beencoder.type.TypeMeta;

/**
 * Created by tityenok on 3/14/15.
 */
public class ElementBuilderFactory
{
  public ElementBuilder createBuilder(TypeMeta typeMeta, Character token)
  {
    switch (typeMeta)
    {
      case INTEGER:
      {
        return new IntegerElementBuilder();
      }
      case STRING:
      {
        return new StringElementBuilder(token);
      }
      case LIST:
      {
        return new ListElementBuilder();
      }
      case DICTIONARY:
      {
        return null;
      }
    }
    return null;
  }
}
