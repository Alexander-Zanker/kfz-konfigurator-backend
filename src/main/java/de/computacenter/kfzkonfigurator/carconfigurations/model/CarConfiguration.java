package de.computacenter.kfzkonfigurator.carconfigurations.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Sonderausstattung;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.SonderausstattungListDeserializer;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.SonderausstattungListSerializer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarConfiguration {
    private Long id;
    private Motorleistung motorleistung;
    private Lackierung lackierung;
    private Felgen felgen;

    @JsonDeserialize(using = SonderausstattungListDeserializer.class)
    @JsonSerialize(using = SonderausstattungListSerializer.class)
    private List<Sonderausstattung> sonderausstattungen;

    private BigDecimal price;

    public CarConfiguration() {
        this.id = 0L;
        this.motorleistung = Motorleistung.LEISTUNG_100;
        this.lackierung = Lackierung.STANDARD;
        this.felgen = Felgen.STANDARD;
        this.sonderausstattungen = new ArrayList<>();
    }

    public CarConfiguration(Motorleistung motorleistung, Lackierung lackierung, Felgen felgen, List<Sonderausstattung> sonderausstattungen) {
        this.motorleistung = motorleistung;
        this.lackierung = lackierung;
        this.felgen = felgen;
        this.sonderausstattungen = sonderausstattungen;
        this.updatePrice();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Motorleistung getMotorleistung() {
        return motorleistung;
    }
    public void setMotorleistung(Motorleistung motorleistung) {
        this.motorleistung = motorleistung;
        this.updatePrice();
    }
    public Lackierung getLackierung() {
        return lackierung;
    }
    public void setLackierung(Lackierung lackierung) {
        this.lackierung = lackierung;
        this.updatePrice();
    }
    public Felgen getFelgen() {
        return felgen;
    }
    public void setFelgen(Felgen felgen) {
        this.felgen = felgen;
        this.updatePrice();
    }
    public List<Sonderausstattung> getSonderausstattungen() {
        return sonderausstattungen;
    }
    public void setSonderausstattungen(List<Sonderausstattung> sonderausstattungen) {
        if (sonderausstattungen != null && sonderausstattungen.size() > 5) {
            throw new IllegalArgumentException("Maximal 5 Sonderausstattungen erlaubt");
        }
        this.sonderausstattungen = sonderausstattungen;
        this.updatePrice();
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void updatePrice() {
        this.price = this.calculatePrice();
    }

    public BigDecimal calculatePrice() {
        BigDecimal price = BigDecimal.ZERO;

        price = price.add(BigDecimal.valueOf(this.getMotorleistung().getPrice()));
        price = price.add(BigDecimal.valueOf(this.getLackierung().getPrice()));
        price = price.add(BigDecimal.valueOf(this.getFelgen().getPrice()));

        if (this.getSonderausstattungen() != null) {
            for (Sonderausstattung option : this.getSonderausstattungen()) {
                price = price.add(BigDecimal.valueOf(option.getPrice()));
            }
        }
        return price;
    }
}
