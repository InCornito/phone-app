package es.masmovil.phonecatalog.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.net.URI;

@Document(collection = "phones")
public class Phone extends Entity {

    @Indexed(unique = true)
    private String name;
    private URI image;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(final URI image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public interface Meta {
        String PRICE = "price";
    }
}
