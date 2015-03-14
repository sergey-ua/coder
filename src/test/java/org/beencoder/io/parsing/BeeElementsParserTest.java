package org.beencoder.io.parsing;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Spy;

/**
 * Created by tityenok on 3/14/15.
 */
public class BeeElementsParserTest
{

  @Test
  public void parseInteger()
  {
    char[] integerValue = new char[] {'i','1','2','e'};
    BeeElementsParser p = new BeeElementsParser();
    feedChars(p,integerValue);
    Assert.assertNotNull(p.getParsedObject());
    Assert.assertEquals(12,p.getParsedObject().getValue());
  }

  @Test
  public void parseString()
  {
    char[] stringValue = new char[] {'3',':','h','e','y'};
    BeeElementsParser p = new BeeElementsParser();
    feedChars(p,stringValue);
    Assert.assertNotNull(p.getParsedObject());
    Assert.assertEquals("hey",p.getParsedObject().getValue());
  }

  private void feedChars(BeeElementsParser parser, char[] tokens)
  {
    for (char token : tokens)
    {
      parser.feedToken(token);
    }
  }

}
