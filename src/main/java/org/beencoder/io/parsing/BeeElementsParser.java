package org.beencoder.io.parsing;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.builder.ContainerElementBuilder;
import org.beencoder.type.builder.ElementBuilder;
import org.beencoder.type.builder.ElementBuilderFactory;
import org.beencoder.type.builder.ValueElementBuilder;
import org.beencoder.type.element.BeeElement;

/**
 * Created by tityenok on 3/14/15.
 */
public class BeeElementsParser
{
  private UnderConstructionBuildersHolder underConstructBuilders = new UnderConstructionBuildersHolder();
  private ElementBuilderFactory builderFactory = new ElementBuilderFactory();

  private BeeElement parsedObject;

  private boolean newBuilderWasCreated;

  public void feedToken(char token)
  {
    parsedObject = null;
    ElementBuilder b = getOrCreateBuilder(token);
    if (this.newBuilderWasCreated)
    {
      registerNewBuilder(b);
      return;
    }
    completeOrAddToValue(b, token);
  }

  private void registerNewBuilder(ElementBuilder builder)
  {
    this.newBuilderWasCreated = false;
    underConstructBuilders.push(builder);
  }


  private ElementBuilder getOrCreateBuilder(char token)
  {
    ElementBuilder currentBuilder = underConstructBuilders.pop();
    if (currentBuilder==null || currentBuilder.canContainElements()){
       return tryConstructNewBuilder(token);
    }
    return currentBuilder;
  }

  private ElementBuilder tryConstructNewBuilder(char token)
  {
    TypeMeta typeMeta = TypeMeta.defineByFirstChar(token);
    if (typeMeta == null)
    {
      //TODO: exception. Unknown type which starts with token
    }
    this.newBuilderWasCreated = true;
    return builderFactory.createBuilder(typeMeta,token);
  }

  private void completeOrAddToValue(ElementBuilder builder, char token)
  {
    if (builder.canBeCompletedWith(token))
    {
      complete(builder);
    }
    else
    {
      //only value builders come to this branch, because we either completed container tag, or created
      //new builder. if it is not, then container has invalid closing character
      raiseExceptionIfBuilderIsNotValue(builder);
      handleValueToken((ValueElementBuilder) builder,token);

    }
  }

  private void complete(ElementBuilder builder)
  {
    BeeElement element = builder.build();
    ContainerElementBuilder parent = underConstructBuilders.getParentContainer();
    if (parent != null)
    {
      parent.appendElement(element);
    }
    this.parsedObject = element;
  }

  private void raiseExceptionIfBuilderIsNotValue(ElementBuilder builder)
  {

  }

  private void handleValueToken(ValueElementBuilder valueBuilder, char token)
  {
    if (valueBuilder.isTokenApplicable(token))
    {
      valueBuilder.addToken(token);
      underConstructBuilders.push(valueBuilder);
    }
    else
    {
      raiseInvalidValueException(valueBuilder, token);
    }
  }

  private void raiseInvalidValueException(ValueElementBuilder valueBuilder, char token)
  {

  }

  public boolean isObjectReady()
  {
    return parsedObject != null && underConstructBuilders.size() == 0;
  }

  public BeeElement getParsedObject()
  {
    return parsedObject;
  }
}
