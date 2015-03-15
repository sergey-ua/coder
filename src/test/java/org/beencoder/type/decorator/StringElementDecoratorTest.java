package org.beencoder.type.decorator;

import org.beencoder.type.element.StringBeeElement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tityenok on 3/15/15.
 */
public class StringElementDecoratorTest
{
  @Test
  public void testOnSimpleString()
  {
    StringElementDecorator stringElementDecorator = new StringElementDecorator();
    StringBeeElement stringBeeElement = StringBeeElement.fromString("abc");
    Assert.assertEquals("3:abc", stringElementDecorator.formatElement(stringBeeElement));
  }

  @Test
  public void testOnEptyString()
  {
    StringElementDecorator stringElementDecorator = new StringElementDecorator();
    StringBeeElement stringBeeElement = StringBeeElement.fromString("");
    Assert.assertEquals("0:", stringElementDecorator.formatElement(stringBeeElement));
  }
}
