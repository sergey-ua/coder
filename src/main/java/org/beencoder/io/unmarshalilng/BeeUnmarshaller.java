package org.beencoder.io.unmarshalilng;

import org.beencoder.excpetion.ParsingException;
import org.beencoder.io.parsing.BeeElementsParser;
import org.beencoder.type.element.BeeElement;

/**
 * Created by tityenok on 3/15/15.
 */
public class BeeUnmarshaller
{
  private BeeElementsParser parser = new BeeElementsParser();

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
