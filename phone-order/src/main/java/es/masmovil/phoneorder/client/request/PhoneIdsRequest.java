package es.masmovil.phoneorder.client.request;

import com.google.common.base.MoreObjects;

import java.util.List;

public class PhoneIdsRequest {

    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(final List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ids", ids)
                .toString();
    }
}
