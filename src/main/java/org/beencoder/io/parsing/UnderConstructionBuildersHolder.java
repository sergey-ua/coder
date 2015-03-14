package org.beencoder.io.parsing;

import com.sun.istack.internal.Nullable;
import org.beencoder.type.builder.ContainerElementBuilder;
import org.beencoder.type.builder.ElementBuilder;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tityenok on 3/14/15.
 */
class UnderConstructionBuildersHolder
{
  private Deque<ElementBuilder> nestingBuilders = new LinkedList<ElementBuilder>();

  void push(ElementBuilder b)
  {
    assertParentIsContainer(nestingBuilders.peekFirst());
    nestingBuilders.push(b);
  }

  @Nullable
  ElementBuilder pop()
  {
    if (nestingBuilders.size() >= 1)
    {
      return nestingBuilders.pop();
    }
    return null;
  }

  int size()
  {
    return nestingBuilders.size();
  }

  private void assertParentIsContainer(@Nullable ElementBuilder parent)
  {
    if (parent != null)
    {
      if (!(parent instanceof ContainerElementBuilder))
      {
        //TODO throw value element is not closed
      }
    }
  }

  @Nullable
  ContainerElementBuilder getParentContainer()
  {
    if (nestingBuilders.size() >= 1)
    {
      ElementBuilder parent = nestingBuilders.getFirst();
      assertParentIsContainer(parent);
      return (ContainerElementBuilder) parent;
    }
    return null;
  }
}
