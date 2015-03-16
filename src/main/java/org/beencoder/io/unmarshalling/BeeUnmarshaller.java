package org.beencoder.io.unmarshalling;

import org.beencoder.excpetion.ParsingException;
import org.beencoder.io.parsing.BeeElementsParser;
import org.beencoder.type.element.BeeElement;

/**
 * Unmarshalls only 1 object at one call. If you need to get sequence of objects from a
 * stream you can use {@link org.beencoder.io.parsing.BeeElementsParser} to get objects
 * one by one.
 * This can parse complex objects which are inside one objects e.g d1:ai5e1:bli1e2:bai3ee1:dd5:hello5:worldee
 * but will not parse i1ei2e because it is not wrapped in one object.
 *
 * Created by tityenok on 3/15/15.
 */
public class BeeUnmarshaller
{
  private final BeeElementsParser parser = new BeeElementsParser();

  public BeeElement unmarshall(byte[] serializedElement) throws ParsingException
  {
    for (byte b : serializedElement)
    {
      parser.feedToken((char) b);
    }
    if (parser.isObjectReady())
    {
      return parser.getParsedObject();
    }
    else
    {
      throw new ParsingException("Cannot complete object building from given bytes");
    }
  }
}
