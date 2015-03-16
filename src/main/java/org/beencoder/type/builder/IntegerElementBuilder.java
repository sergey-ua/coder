package org.beencoder.type.builder;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.IntegerBeeElement;

/**
 * Created by tityenok on 3/14/15.
 */
public class IntegerElementBuilder extends ValueElementBuilder<IntegerBeeElement>
{
  private String value = "";


  @Override
  public boolean isTokenApplicable(char token)
  {
    return Character.isDigit(token) || canBeMinus(token);
  }

  private boolean canBeMinus(char token)
  {
    return '-' == token && value.isEmpty();
  }

  @Override
  public void addToken(char token)
  {
    value +=String.valueOf(token);
  }

  @Override
  public IntegerBeeElement build()
  {
    return IntegerBeeElement.fromInt(Integer.parseInt(value));

  }

  @Override
  public boolean canBeCompletedWith(char token)
  {
    return TypeMeta.INTEGER.getEndPattern().equals(token);
  }

  @Override
  public TypeMeta getSupportedTypeMeta()
  {
    return TypeMeta.INTEGER;
  }
}
