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
    return Character.isDigit(token);
  }

  @Override
  public void addToken(char token)
  {
    value +=String.valueOf(token);
  }

  @Override
  public IntegerBeeElement build()
  {
    try
    {
      return IntegerBeeElement.fromInt(Integer.parseInt(value));
    }
    catch (NullPointerException ex)
    {
      return null;
    }
  }

  @Override
  public boolean canBeCompletedWith(char token)
  {
    return TypeMeta.INTEGER.getEndPattern().equals(token);
  }
}
