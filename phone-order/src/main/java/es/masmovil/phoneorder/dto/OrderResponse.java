package es.masmovil.phoneorder.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private String id;

    private List<String> availableItemIds = List.of();
    private List<String> unavailableItemIds = List.of();

    private BigDecimal total;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<String> getAvailableItemIds() {
        return availableItemIds;
    }

    public void setAvailableItemIds(final List<String> availableItemIds) {
        this.availableItemIds = availableItemIds;
    }

    public List<String> getUnavailableItemIds() {
        return unavailableItemIds;
    }

    public void setUnavailableItemIds(final List<String> unavailableItemIds) {
        this.unavailableItemIds = unavailableItemIds;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
}
