package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Sonderausstattung;

import java.io.IOException;
import java.util.List;

public class SonderausstattungListSerializer extends StdSerializer<List<Sonderausstattung>> {

    public SonderausstattungListSerializer() {
        this(null);
    }

    public SonderausstattungListSerializer(Class<List<Sonderausstattung>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Sonderausstattung> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        if (value != null) {
            for (Sonderausstattung sa : value) {
                gen.writeString(sa.getBezeichnung());
            }
        }
        gen.writeEndArray();
    }
}
