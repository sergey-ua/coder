Project can deserialize from and serialize to b-encode format.

B-Encode description was taken from:

https://en.wikipedia.org/wiki/Bencode

Build

mvn clean install

This will:

- Compile jar file
- Run unit tests
- Run acceptance (integration) tests using Concordion
- Provide nice looking results of acceptance testing(please see target/deserialization-serialization-tests/DeserializarionSerialization.html)

How to play with it:

- Open  src/test/specs/DeserializationSerialization.html
- This HTML file contains b-encoded strings which will be deserialized using unmarshaller, then serialized again using marshaller.
- Input string value and string value from marshaller will be compared
- Add new or change existing tests in HTML file
- Run build again to check if tests pass

Author: Sergey Tityenok (tityenok@gmail.com)