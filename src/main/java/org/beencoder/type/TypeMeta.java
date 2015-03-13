package org.beencoder.type;

import java.io.Serializable;

/**
 * Created by tityenok on 3/13/15.
 */
public enum TypeMeta
{
  INTEGER("i","e"), STRING("\\d", null), LIST("l","e"), DICTIONARY("d","e");

  private final String startsWith;
  private final String endsWith;

  TypeMeta(String startsWith, String endsWith)
  {
    this.startsWith = startsWith;
    this.endsWith = endsWith;
  }

  public String getStartsWith()
  {
    return startsWith;
  }

  public String getEndsWith()
  {
    return endsWith;
  }
}
