package es.masmovil.phoneorder.client;

import es.masmovil.phoneorder.client.request.PhoneIdsRequest;
import es.masmovil.phoneorder.controller.OrderController;
import es.masmovil.phoneorder.dto.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public final class PhoneCatalogClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final String PHONES_BATCH_PRICES = "/phones/batch-prices";

    @Value("${services.phone-catalog.host}")
    private String phoneCatalogHost;

    @Value("${services.phone-catalog.port}")
    private String phoneCatalogPort;

    private final WebClient.Builder webClientBuilder;
    private final String BASE_URL;

    //it would be better to have separate config file
    public PhoneCatalogClient(@Value("${services.gateway.base-url}") final String baseUrl) {
        this.webClientBuilder = WebClient
                .builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .baseUrl(baseUrl);
        this.BASE_URL = baseUrl;
    }

    public Flux<Phone> findByIdIn(final List<String> ids) {
        final PhoneIdsRequest phoneIdsRequest = new PhoneIdsRequest();
        phoneIdsRequest.setIds(ids);

        LOGGER.debug("Inside PhoneCatalogClient.findByIdIn. baseUrl = '{}', uri = '{}'", BASE_URL, PHONES_BATCH_PRICES);
        return webClientBuilder.build()
                .post()
                .uri(PHONES_BATCH_PRICES)
                .body(BodyInserters.fromObject(phoneIdsRequest))
                .retrieve()
                .bodyToFlux(Phone.class);
    }
}
