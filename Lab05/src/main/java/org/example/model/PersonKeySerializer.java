package org.example.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class PersonKeySerializer extends StdSerializer<Person> {

    public PersonKeySerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeFieldName(person.name());
    }
}