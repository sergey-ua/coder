package org.beencoder.type.decorator;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.ListBeeElement;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tityenok on 3/15/15.
 */
public class ListElementDecoratorTest
{
  @Test
  public void testOnListLists()
  {
    ElementDecoratorFactory factory = Mockito.mock(ElementDecoratorFactory.class);
    ListElementDecorator listDecorator = new ListElementDecorator(factory);
    Mockito.doReturn(listDecorator).when(factory).getOrCreateDecoratorForElement(TypeMeta.LIST);
    ListBeeElement listElement = createListOfLists();
    String formatted = listDecorator.formatElement(listElement);
    Assert.assertEquals("llee",formatted);
  }

  private ListBeeElement createListOfLists()
  {
    List<ListBeeElement> mainList = new LinkedList<>();
    mainList.add(new ListBeeElement(new LinkedList<ListBeeElement>()));
    return new ListBeeElement(mainList);
  }
}
