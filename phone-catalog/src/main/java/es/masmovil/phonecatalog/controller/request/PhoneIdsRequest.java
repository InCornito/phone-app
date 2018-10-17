package es.masmovil.phonecatalog.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PhoneIdsRequest {

    @NotNull
    @NotEmpty
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(final List<String> ids) {
        this.ids = ids;
    }
}
