package org.beencoder.excpetion;

/**
 * Created by tityenok on 3/14/15.
 */
public class ParsingException extends Exception
{
  public ParsingException()
  {
    super();
  }

  public ParsingException(String message)
  {
    super(message);
  }

  public ParsingException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
