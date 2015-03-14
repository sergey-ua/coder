package org.beencoder.type;

import com.sun.istack.internal.Nullable;

/**
 * Created by tityenok on 3/13/15.
 */
public enum TypeMeta
{
  INTEGER('i','e'), LIST('l','e'), DICTIONARY('d','e'),STRING();

  private final Character startPattern;
  private final Character endPattern;

  TypeMeta(char startPattern, char endPattern)
  {
    this.startPattern = startPattern;
    this.endPattern = endPattern;
  }

  TypeMeta()
  {
    this.startPattern = null;
    this.endPattern = null;
  }

  public Character getStartPattern()
  {
    return startPattern;
  }

  public Character getEndPattern()
  {
    return endPattern;
  }

  @Nullable
  public static TypeMeta defineByFirstChar(char ch)
  {
    if (Character.isDigit(ch))
    {
      return STRING;
    }
    for (TypeMeta typeMeta : TypeMeta.values())
    {
      if (typeMeta.getStartPattern() != null && typeMeta.getStartPattern().equals(ch))
      {
        return typeMeta;
      }
    }
    return null;
  }
}
