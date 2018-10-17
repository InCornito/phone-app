package es.masmovil.phoneorder.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

public abstract class Entity implements Storable {

    @Id
    protected String id;

    @CreatedDate
    protected Long createdAt;

    @LastModifiedDate
    protected Long lastModifiedAt;

    @CreatedBy
    protected String createdBy;

    @Version
    protected Long version;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(final Long lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity entity = (Entity) o;
        return Objects.equal(id, entity.id) &&
                Objects.equal(createdAt, entity.createdAt) &&
                Objects.equal(lastModifiedAt, entity.lastModifiedAt) &&
                Objects.equal(createdBy, entity.createdBy) &&
                Objects.equal(version, entity.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, createdAt, lastModifiedAt, createdBy, version);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("createdAt", createdAt)
                .add("lastModifiedAt", lastModifiedAt)
                .add("createdBy", createdBy)
                .add("version", version)
                .toString();
    }
}
