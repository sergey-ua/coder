package org.beencoder.io.parsing;

import org.beencoder.excpetion.InvalidStatementException;
import org.beencoder.excpetion.InvalidValueException;
import org.beencoder.excpetion.ParsingException;
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

  public void feedToken(char token) throws ParsingException
  {
    parsedObject = null;
    try
    {
      ElementBuilder b = getOrCreateBuilder(token);
      if (this.newBuilderWasCreated)
      {
        registerNewBuilder(b);
        return;
      }
      completeOrAddToValue(b, token);
    }
    catch (InvalidStatementException | InvalidValueException e)
    {
      throw new ParsingException("Exception during parsing process. See stacktrace for details", e);
    }

  }

  private void registerNewBuilder(ElementBuilder builder) throws InvalidStatementException
  {
    this.newBuilderWasCreated = false;
    underConstructBuilders.push(builder);
  }


  private ElementBuilder getOrCreateBuilder(char token) throws InvalidStatementException
  {
    ElementBuilder currentBuilder = underConstructBuilders.pop();
    if (currentBuilder==null || currentBuilder.canContainElements()){
       return tryConstructNewBuilder(token);
    }
    return currentBuilder;
  }

  private ElementBuilder tryConstructNewBuilder(char token) throws InvalidStatementException
  {
    TypeMeta typeMeta = TypeMeta.defineByFirstChar(token);
    if (typeMeta == null)
    {
      throw new InvalidStatementException("Unknown element: " + token);
    }
    this.newBuilderWasCreated = true;
    return builderFactory.createBuilder(typeMeta,token);
  }

  private void completeOrAddToValue(ElementBuilder builder, char token) throws InvalidValueException, InvalidStatementException
  {
    if (builder.canBeCompletedWith(token))
    {
      complete(builder);
    }
    else
    {
      //only value builders come to this branch, because we either completed container tag, or created
      //new builder. if it is not, then container has invalid closing character
      raiseExceptionIfBuilderIsNotValue(builder, token);
      handleValueToken((ValueElementBuilder) builder,token);

    }
  }

  private void complete(ElementBuilder builder) throws InvalidStatementException
  {
    BeeElement element = builder.build();
    ContainerElementBuilder parent = underConstructBuilders.getParentContainer();
    if (parent != null)
    {
      parent.appendElement(element);
    }
    this.parsedObject = element;
  }

  private void raiseExceptionIfBuilderIsNotValue(ElementBuilder builder, char token) throws InvalidStatementException
  {
    throw new InvalidStatementException(String.format("Invalid closing character for container of type %s " +
        ". Found: %s.",builder.getSupportedTypeMeta().toString(),token));
  }

  private void handleValueToken(ValueElementBuilder valueBuilder, char token) throws InvalidValueException, InvalidStatementException
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

  private void raiseInvalidValueException(ValueElementBuilder valueBuilder, char token) throws InvalidValueException
  {
    throw new InvalidValueException(String.format("Cannot apply value %s to type of %s", token,
        valueBuilder.getSupportedTypeMeta().toString()));
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
