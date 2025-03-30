package de.computacenter.kfzkonfigurator.orders;

public class OrderResponse {
    private final Order order;
    private final String urlPath;

    public OrderResponse(Order order, String urlPath) {
        this.order = order;
        this.urlPath = urlPath;
    }

    public Order getOrder() {
        return order;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
