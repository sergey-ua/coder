import org.beencoder.excpetion.ParsingException;
import org.beencoder.io.marshalling.BeeMarshaller;
import org.beencoder.io.unmarshalling.BeeUnmarshaller;
import org.beencoder.type.element.BeeElement;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

/**
 * Created by tityenok on 3/15/15.
 */
@RunWith(ConcordionRunner.class)
public class DeserializarionSerializationFixture
{

  public boolean ifAfterSerializationMatch(String serialized) throws ParsingException
  {
    BeeUnmarshaller unmarshaller = new BeeUnmarshaller();
    BeeElement element = unmarshaller.unmarshall(serialized.trim().getBytes());
    BeeMarshaller marshaller = new BeeMarshaller();
    String newSerializedVersion = new String(marshaller.marshall(element));
    return newSerializedVersion.equals(serialized.trim());

  }
}
