package org.beencoder.io.parsing;

import com.sun.istack.internal.Nullable;
import org.beencoder.excpetion.InvalidStatementException;
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

  void push(@Nullable ElementBuilder b) throws InvalidStatementException
  {
    if (b != null)
    {
      assertParentIsContainer(nestingBuilders.peekFirst());
      nestingBuilders.push(b);
    }
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

  private void assertParentIsContainer(@Nullable ElementBuilder parent) throws InvalidStatementException
  {
    if (parent != null)
    {
      if (!(parent instanceof ContainerElementBuilder))
      {
        // if we have started to parse another element and previous is still not completed
        //and its not container then consider it as user did not close value element properly
        throw new InvalidStatementException(String.format("Element of type %s is not closed properly",
            parent.getSupportedTypeMeta().toString()));
      }
    }
  }

  @Nullable
  ContainerElementBuilder getParentContainer() throws InvalidStatementException
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
