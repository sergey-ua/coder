package org.beencoder.type.builder;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.StringBeeElement;

/**
 * Created by tityenok on 3/14/15.
 */
public class StringElementBuilder extends ValueElementBuilder<StringBeeElement>
{
  private final StringElementHeaderBuilder headerBuilder = new StringElementHeaderBuilder();
  private final StringBuilder value = new StringBuilder("");
  private Character lastChar;

  public StringElementBuilder(Character character)
  {
     addToken(character);
  }
  @Override
  public boolean isTokenApplicable(char token)
  {
    if (!headerBuilder.isComplete()){
      return Character.isDigit(token) ||
          (StringElementHeaderBuilder.HEADER_SEPARATOR.equals(token) && headerBuilder.canHaveSeparator());
    }
    return headerBuilder.getLength() > value.length();
  }

  @Override
  public void addToken(char token)
  {
    if (!headerBuilder.isComplete())
    {
      headerBuilder.append(token);
    }
    else
    {
      value.append(token);
    }
  }

  @Override
  public StringBeeElement build()
  {
    if (lastChar != null)
    {
      value.append(lastChar);
      lastChar = null;
    }
    return StringBeeElement.fromString(value.toString());
  }

  @Override
  public boolean canBeCompletedWith(char token)
  {
    //if only one character left to complete we need to
    //save this character and use when build will called
    if (headerBuilder.isComplete() && value.length()==headerBuilder.getLength()-1)
    {
      lastChar = token;
      return true;
    }
    return headerBuilder.isEmptyString(token);
  }



  @Override
  public TypeMeta getSupportedTypeMeta()
  {
    return TypeMeta.STRING;
  }
}
