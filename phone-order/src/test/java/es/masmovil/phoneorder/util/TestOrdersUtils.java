package es.masmovil.phoneorder.util;

import es.masmovil.phoneorder.dto.OrderRequest;
import es.masmovil.phoneorder.dto.OrderResponse;

import java.math.BigDecimal;
import java.util.List;

public class TestOrdersUtils {

    private final static String TEST_UUID = "testUUID";
    public static final String TEST_PHONE_ID = "5bc617770e8e3d00066c802d";
    public static final int TEST_TOTAL = 500;

    //it would be better to use builder in such places
    public static OrderRequest testOrderRequest() {
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(TEST_UUID);
        orderRequest.setUserName("testUserName");
        orderRequest.setuserSurname("testUserSurname");
        orderRequest.setUserEmail("testUserEmail@gmail.com");
        orderRequest.setPhoneIds(List.of(TEST_PHONE_ID));
        return orderRequest;
    }

    public static OrderResponse testOrderResponse() {
        final OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(TEST_UUID);
        orderResponse.setTotal(BigDecimal.valueOf(TEST_TOTAL));
        orderResponse.setAvailableItemIds(List.of(TEST_PHONE_ID));
        return orderResponse;
    }
}
