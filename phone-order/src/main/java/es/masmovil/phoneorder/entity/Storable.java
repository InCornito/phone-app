package es.masmovil.phoneorder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

public interface Storable extends Persistable<String> {

    Long getCreatedAt();

    Long getVersion();

    String getCreatedBy();

    @JsonIgnore
    default boolean isNew() {
        return (Objects.isNull(getVersion()) || getVersion() == 0L) && Objects.isNull(getCreatedAt());
    }
}
