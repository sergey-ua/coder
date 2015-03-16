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
 * Parses sequence of chars one by one. Use {@code feedToken(char)} in pair with {@code isObjectReady}.
 * Once object is ready call {@code getParsedObject}
 * Example
 * <p>
 *    <pre>
 *      {@code
 *   while (..bytes reading)
 *      {
 *        for (byte b in bytes)
 *        {
 *           beeElementsParser.feedToken((char) b);
 *           if (beeElementsParser.isObjectReady())
 *           {
 *             readObjects.add(beeElementsParser.getParsedObject())
 *           }
 *        }
 *      }
 *      }
 *    </pre>
 * </p>
 *
 * @author tityenok
 */
public class BeeElementsParser
{
  private final UnderConstructionBuildersHolder underConstructBuilders = new UnderConstructionBuildersHolder();
  private final ElementBuilderFactory builderFactory = new ElementBuilderFactory();

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
    if (currentBuilder == null || canAddItemsToContainer(currentBuilder, token))
    {
      underConstructBuilders.push(currentBuilder);
      return tryConstructNewBuilder(token);
    }
    return currentBuilder;
  }

  private boolean canAddItemsToContainer( ElementBuilder builder, char token)
  {
    return builder.canContainElements() && ! builder.canBeCompletedWith(token);
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

  private void complete(ElementBuilder builder) throws InvalidStatementException, InvalidValueException
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
    if (!(builder instanceof ValueElementBuilder))
    {
      throw new InvalidStatementException(String.format("Invalid closing character for container of type %s " +
          ". Found: %s.", builder.getSupportedTypeMeta().toString(), token));
    }
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
