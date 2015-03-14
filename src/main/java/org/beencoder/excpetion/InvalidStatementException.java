package org.beencoder.excpetion;

/**
 * Created by tityenok on 3/14/15.
 */
public class InvalidStatementException extends Exception
{
  public InvalidStatementException()
  {
    super();
  }

  public InvalidStatementException(String message)
  {
    super(message);
  }
}
