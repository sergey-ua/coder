package org.beencoder.type.decorator;

import org.beencoder.type.element.IntegerBeeElement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tityenok on 3/15/15.
 */
public class IntegerElementDecoratorTest
{
  @Test
  public void testOnPositiveInteger()
  {
    IntegerElementDecorator integerElementDecorator = new IntegerElementDecorator();
    IntegerBeeElement integerBeeElement = IntegerBeeElement.fromInt(10);
    Assert.assertEquals("i10e", integerElementDecorator.formatElement(integerBeeElement));
  }

  @Test
  public void testOnNegativeInteger()
  {
    IntegerElementDecorator integerElementDecorator = new IntegerElementDecorator();
    IntegerBeeElement integerBeeElement = IntegerBeeElement.fromInt(-10);
    Assert.assertEquals("i-10e", integerElementDecorator.formatElement(integerBeeElement));
  }
}
