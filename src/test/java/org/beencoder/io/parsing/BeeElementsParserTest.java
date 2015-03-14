package org.beencoder.io.parsing;

import org.beencoder.excpetion.ParsingException;
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
    shouldFeedCharsOk(p, integerValue);
    Assert.assertNotNull(p.getParsedObject());
    Assert.assertEquals(12,p.getParsedObject().getValue());
  }

  @Test
  public void parseString()
  {
    char[] stringValue = new char[] {'3',':','h','e','y'};
    BeeElementsParser p = new BeeElementsParser();
    shouldFeedCharsOk(p, stringValue);
    Assert.assertNotNull(p.getParsedObject());
    Assert.assertEquals("hey",p.getParsedObject().getValue());
  }

  @Test(expected = ParsingException.class)
  public void parseInvalidNumber() throws ParsingException
  {
    char[] invalidInteger = new char[]{'i', '1', 'g', 'e'};
    BeeElementsParser p = new BeeElementsParser();
    feedChars(p,invalidInteger);
  }

  private void shouldFeedCharsOk(BeeElementsParser parser, char[] tokens)
  {
    try
    {
      feedChars(parser,tokens);
    }
    catch (ParsingException e)
    {
      Assert.fail();
    }
  }
  private void feedChars(BeeElementsParser parser, char[] tokens) throws ParsingException
  {
    for (char token : tokens)
    {
      parser.feedToken(token);
    }
  }

}
