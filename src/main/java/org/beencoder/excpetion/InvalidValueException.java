package org.beencoder.excpetion;

/**
 * Created by tityenok on 3/14/15.
 */
public class InvalidValueException extends Exception
{
  public InvalidValueException()
  {
    super();
  }

  public InvalidValueException(String message)
  {
    super(message);
  }
}
