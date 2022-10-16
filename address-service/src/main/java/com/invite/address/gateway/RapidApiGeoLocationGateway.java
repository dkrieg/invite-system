package com.invite.address.gateway;

import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.Distance;
import com.invite.address.domain.GeoLocation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.RequestEntity.get;

@Component
@EnableConfigurationProperties(GeoLocationGatewayProperties.class)
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
class RapidApiGeoLocationGateway implements GeoLocationGateway {
    GeoLocationGatewayProperties properties;

    RestTemplate template;

    @Override
    public GeoLocation getGeoLocation(AddressRequest request) {
        URI uri = UriComponentsBuilder.fromUriString(properties.forward.endpoint).build(
                Map.of("line1", request.getLine1(),
                        "city", request.getCity(),
                        "state", request.getState(),
                        "postalCode", request.getZipCode().getPostalCode())
        );

        List<LonLatResponse> response = requireNonNull(template.exchange(get(uri)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(List.of(APPLICATION_JSON));
                    httpHeaders.set("X-RapidAPI-Key", properties.key);
                    httpHeaders.set("X-RapidAPI-Host", properties.forward.host);
                }).build(), new ParameterizedTypeReference<List<LonLatResponse>>() {
        }).getBody());
        return GeoLocation.builder()
                .latitude(Double.valueOf(response.get(0).lat))
                .longitude(Double.valueOf(response.get(0).lon))
                .build();
    }

    @Override
    public Distance calculateDistance(GeoLocation start, GeoLocation end) {
        URI uri = UriComponentsBuilder.fromUriString(properties.distance.endpoint).build(
                Map.of("lat1", start.getLatitude(),
                        "lon1", start.getLongitude(),
                        "lat2", end.getLatitude(),
                        "lon2", end.getLongitude())
        );
        DistanceResponse response = requireNonNull(template.exchange(get(uri)
                .headers(httpHeaders -> {
                    httpHeaders.setAccept(List.of(APPLICATION_JSON));
                    httpHeaders.set("X-RapidAPI-Key", properties.key);
                    httpHeaders.set("X-RapidAPI-Host", properties.distance.host);
                }).build(), DistanceResponse.class).getBody());
        return Distance.builder()
                .start(start)
                .end(end)
                .miles(new BigDecimal(response.distance).setScale(1, HALF_UP).doubleValue())
                .build();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class LonLatResponse {
        String lon;
        String lat;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class DistanceResponse {
        Double distance;
    }
}
