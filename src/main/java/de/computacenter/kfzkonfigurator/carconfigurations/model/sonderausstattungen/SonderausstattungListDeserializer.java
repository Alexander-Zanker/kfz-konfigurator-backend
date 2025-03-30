package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SonderausstattungListDeserializer extends StdDeserializer<List<Sonderausstattung>> {

    public SonderausstattungListDeserializer() {
        this(null);
    }

    public SonderausstattungListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Sonderausstattung> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Sonderausstattung> list = new ArrayList<>();
        if (p.getCurrentToken() == JsonToken.START_ARRAY) {
            while (p.nextToken() != JsonToken.END_ARRAY) {
                String bezeichnung = p.getValueAsString();
                Sonderausstattung sa = createSonderausstattungFromBezeichnung(bezeichnung);
                if (sa != null) {
                    list.add(sa);
                }
            }
        } else {
            String bezeichnung = p.getValueAsString();
            Sonderausstattung sa = createSonderausstattungFromBezeichnung(bezeichnung);
            if (sa != null) {
                list.add(sa);
            }
        }
        return list;
    }

    private Sonderausstattung createSonderausstattungFromBezeichnung(String bezeichnung) {
        return switch (bezeichnung) {
            case "Klimaanlage" -> new Klimaanlage();
            case "Soundsystem" -> new Soundsystem();
            case "Fahrsicherheitssystem" -> new Fahrsicherheitssystem();
            case "AbgedunkelteScheiben" -> new AbgedunkelteScheiben();
            case "Rückfahrkamera" -> new Rueckfahrkamera();
            default -> null;
        };
    }
}
