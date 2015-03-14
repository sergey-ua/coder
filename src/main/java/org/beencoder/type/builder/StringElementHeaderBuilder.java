package org.beencoder.type.builder;

/**
 * Created by tityenok on 3/14/15.
 */
class StringElementHeaderBuilder
{
  public static final Character HEADER_SEPARATOR = ':';

  private StringBuilder lengthRawValue = new StringBuilder("");
  private Integer length;

  public void append(char headerChar)
  {
    if (isComplete())
    {
      return;
    }
    if (Character.isDigit(headerChar))
    {
      lengthRawValue.append(headerChar);
    } else
    {
      if (HEADER_SEPARATOR.equals(headerChar))
      {
        completeHeader();
      }
    }
  }

  public boolean canHaveSeparator()
  {
    return lengthRawValue.length()!=0;
  }

  private void completeHeader()
  {
    length = Integer.parseInt(lengthRawValue.toString());
  }

  public boolean isComplete()
  {
    return length != null;
  }

  public Integer getLength()
  {
    return length;
  }
}
