package org.beencoder.io.parsing;

import org.beencoder.excpetion.ParsingException;
import org.beencoder.type.element.IntegerBeeElement;
import org.beencoder.type.element.ListBeeElement;
import org.junit.Assert;
import org.junit.Test;

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

  @Test
  public void parseStringOfNumbers()
  {
    char[] stringValue = new char[] {'3',':','1','2','3'};
    BeeElementsParser p = new BeeElementsParser();
    shouldFeedCharsOk(p, stringValue);
    Assert.assertNotNull(p.getParsedObject());
    Assert.assertEquals("123",p.getParsedObject().getValue());
  }

  @Test(expected = ParsingException.class)
  public void parseInvalidNumber() throws ParsingException
  {
    char[] invalidInteger = new char[]{'i', '1', 'g', 'e'};
    BeeElementsParser p = new BeeElementsParser();
    feedChars(p,invalidInteger);
  }


  @Test(expected = ParsingException.class)
  public void parseStringWithoutLength() throws ParsingException
  {
    char[] invalidString = new char[]{':', 'a', 'b', 'c'};
    BeeElementsParser p = new BeeElementsParser();
    feedChars(p,invalidString);
  }

  @Test
  public void parseListOfIntegers()
  {
    char[] listOfStrs = "li1ei2ei3ee".toCharArray();
    BeeElementsParser p = new BeeElementsParser();
    shouldFeedCharsOk(p, listOfStrs);
    Assert.assertNotNull(p.getParsedObject());
    ListBeeElement listBeeElement = (ListBeeElement) p.getParsedObject();
    Assert.assertNotNull(listBeeElement);
    Assert.assertEquals(3, listBeeElement.getValue().size());

    compareIntegers(1, listBeeElement.getValue().get(0));
    compareIntegers(2, listBeeElement.getValue().get(1));
    compareIntegers(3, listBeeElement.getValue().get(2));

  }

  private void compareIntegers(int expected, Object given)
  {
    if (!(given instanceof IntegerBeeElement))
    {
      Assert.fail("Passed element is not integer");
    }
    IntegerBeeElement intObj = (IntegerBeeElement) given;
    Assert.assertEquals(Integer.valueOf(expected), intObj.getValue());
  }
  private void shouldFeedCharsOk(BeeElementsParser parser, char[] tokens)
  {
    try
    {
      feedChars(parser,tokens);
    }
    catch (ParsingException e)
    {
      e.printStackTrace();
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
