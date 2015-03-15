package org.beencoder.type.decorator;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.MapBeeElement;
import org.beencoder.type.element.StringBeeElement;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tityenok on 3/15/15.
 */
public class MapElementDecoratorTest
{
  @Test
  public void testOnMapOfMap()
  {
    ElementDecoratorFactory factory = Mockito.mock(ElementDecoratorFactory.class);
    MapElementDecorator mapDecorator = new MapElementDecorator(factory);
    StringElementDecorator stringElementDecorator = new StringElementDecorator();
    Mockito.doReturn(mapDecorator).when(factory).getOrCreateDecoratorForElement(TypeMeta.DICTIONARY);
    Mockito.doReturn(stringElementDecorator).when(factory).getOrCreateDecoratorForElement(TypeMeta.STRING);
    MapBeeElement mapBeeElement = createMapOfMaps();
    String formatted = mapDecorator.formatElement(mapBeeElement);
    Assert.assertEquals("d1:ade1:bdee", formatted);
  }

  private MapBeeElement createMapOfMaps()
  {
    Map<StringBeeElement, MapBeeElement> mainMap = new HashMap<>();
    mainMap.put(StringBeeElement.fromString("b"), new MapBeeElement(new HashMap<StringBeeElement, MapBeeElement>()));
    mainMap.put(StringBeeElement.fromString("a"), new MapBeeElement(new HashMap<StringBeeElement, MapBeeElement>()));
    return new MapBeeElement(mainMap);


  }
}
