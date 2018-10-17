package es.masmovil.phoneorder.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
public class Order extends Entity {

    private User user;
    private List<String> userIds;
    private BigDecimal total;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(final List<String> userIds) {
        this.userIds = userIds;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
}
