package es.masmovil.phonecatalog.util;

import es.masmovil.phonecatalog.controller.request.PhoneIdsRequest;
import es.masmovil.phonecatalog.entity.Phone;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;

public class TestPhoneUtils {

    private final static String UUID = "testUUID";

    public static Phone createOneTestPhone() {
        final Phone samsung = new Phone();
        samsung.setId(UUID);
        samsung.setName("samsung");
        samsung.setDescription("Unlocked");
        samsung.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/61tpPLeyBBL._SX679_.jpg"));
        samsung.setPrice(BigDecimal.valueOf(10));
        return samsung;
    }

    public static PhoneIdsRequest createPhoneIdsRequest() {
        final PhoneIdsRequest phoneIdsRequest = new PhoneIdsRequest();
        phoneIdsRequest.setIds(Collections.singletonList(UUID));
        return phoneIdsRequest;
    }
}
