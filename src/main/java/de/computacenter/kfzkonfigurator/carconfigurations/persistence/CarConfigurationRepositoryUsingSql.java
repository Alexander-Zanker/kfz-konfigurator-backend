package de.computacenter.kfzkonfigurator.carconfigurations.persistence;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.model.Felgen;
import de.computacenter.kfzkonfigurator.carconfigurations.model.Lackierung;
import de.computacenter.kfzkonfigurator.carconfigurations.model.Motorleistung;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarConfigurationRepositoryUsingSql implements CarConfigurationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CarConfiguration> carConfigRowMapper = new RowMapper<CarConfiguration>() {
        @Override
        public CarConfiguration mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarConfiguration config = new CarConfiguration();
            config.setId(rs.getLong("id"));
            String motorleistungStr = rs.getString("motorleistung");
            if (motorleistungStr != null) {
                config.setMotorleistung(Enum.valueOf(Motorleistung.class, motorleistungStr));
            }
            String lackierungStr = rs.getString("lackierung");
            if (lackierungStr != null) {
                config.setLackierung(Enum.valueOf(Lackierung.class, lackierungStr));
            }
            String felgenStr = rs.getString("felgen");
            if (felgenStr != null) {
                config.setFelgen(Enum.valueOf(Felgen.class, felgenStr));
            }

            String sonderausstattungenCsv = rs.getString("sonderausstattungen");
            config.setSonderausstattungen(convertCsvToSonderausstattungList(sonderausstattungenCsv));

            config.setPrice(rs.getBigDecimal("price"));

            return config;
        }
    };

    @Override
    public CarConfiguration save(CarConfiguration config) {
        String sql = "INSERT INTO car_configuration (motorleistung, lackierung, felgen, sonderausstattungen, price) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, config.getMotorleistung() != null ? config.getMotorleistung().name() : null);
            ps.setString(2, config.getLackierung() != null ? config.getLackierung().name() : null);
            ps.setString(3, config.getFelgen() != null ? config.getFelgen().name() : null);
            ps.setString(4, convertSonderausstattungListToCsv(config.getSonderausstattungen()));
            ps.setBigDecimal(5, config.getPrice());

            return ps;
        }, keyHolder);
        config.setId(keyHolder.getKey().longValue());

        return config;
    }

    @Override
    public CarConfiguration findById(Long id) {
        String sql = "SELECT * FROM car_configuration WHERE id = ?";
        CarConfiguration config = jdbcTemplate.queryForObject(sql, new Object[]{id}, carConfigRowMapper);
        return config;
    }

    private String convertSonderausstattungListToCsv(List<Sonderausstattung> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.stream()
                .map(Sonderausstattung::getBezeichnung)
                .collect(Collectors.joining(","));
    }

    private List<Sonderausstattung> convertCsvToSonderausstattungList(String csv) {
        List<Sonderausstattung> list = new ArrayList<>();
        if (csv == null || csv.trim().isEmpty()) {
            return list;
        }
        String[] tokens = csv.split(",");
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty()) {
                Sonderausstattung sa = createSonderausstattungFromBezeichnung(token);
                if (sa != null) {
                    list.add(sa);
                }
            }
        }
        return list;
    }

    private Sonderausstattung createSonderausstattungFromBezeichnung(String bezeichner) {
        return switch (bezeichner) {
            case "Klimaanlage" -> new Klimaanlage();
            case "Soundsystem" -> new Soundsystem();
            case "Fahrsicherheitssystem" -> new Fahrsicherheitssystem();
            case "Abgedunkelte Scheiben" -> new AbgedunkelteScheiben();
            case "Rückfahrkamera" -> new Rueckfahrkamera();
            default -> null;
        };
    }
}
