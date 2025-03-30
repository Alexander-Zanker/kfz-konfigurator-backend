package de.computacenter.kfzkonfigurator.orders.persistence;

import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class OrderRepositoryUsingSql implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CarConfigurationRepository carConfigurationRepository;

    private RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            Long configId = rs.getLong("configuration_id");
            CarConfiguration config = carConfigurationRepository.findById(configId);
            order.setCarConfiguration(config);
            Timestamp ts = rs.getTimestamp("order_date");
            order.setOrderDate(ts.toLocalDateTime());
            return order;
        }
    };

    @Override
    public Order save(Order order) {
        String sql = "INSERT INTO orders (configuration_id, order_date) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, order.getCarConfiguration().getId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            return ps;
        }, keyHolder);
        order.setId(keyHolder.getKey().longValue());
        return order;
    }

    @Override
    public Order findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, orderRowMapper);
    }
}
