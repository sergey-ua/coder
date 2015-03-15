package org.beencoder.type.decorator;

import org.beencoder.type.TypeMeta;
import org.beencoder.type.element.BeeElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tityenok on 3/15/15.
 */
public class ElementDecoratorFactory
{

  private Map<TypeMeta, ElementDecorator> decoratorsRepository =
      new HashMap<>();

  public ElementDecorator getOrCreateDecoratorForElement( TypeMeta typeMeta)
  {
    assert typeMeta != null : "Null parameter is not supported";
    return lazyCreate(typeMeta);

  }

  private ElementDecorator lazyCreate(TypeMeta typeMeta)
  {
    ElementDecorator decorator = tryGetFromRepository(typeMeta);
    if (decorator == null)
    {
      decorator = createNewDecorator(typeMeta);
      if (decorator != null)
      {
        registerInRepository(typeMeta, decorator);
      }
    }
    return decorator;
  }

  private ElementDecorator tryGetFromRepository(TypeMeta typeMeta)
  {
    return decoratorsRepository.get(typeMeta);
  }

  private ElementDecorator createNewDecorator(TypeMeta typeMeta)
  {
    switch (typeMeta)
    {
      case INTEGER:
      {
        return new IntegerElementDecorator();
      }
      case STRING:
      {
        return new StringElementDecorator();
      }
      case LIST:
      {
        return new ListElementDecorator(this);
      }
      case DICTIONARY:
      {
        return new MapElementDecorator(this);
      }
    }
    return null;
  }

  private void registerInRepository(TypeMeta typeMeta, ElementDecorator decorator)
  {
    decoratorsRepository.put(typeMeta, decorator);
  }
}
