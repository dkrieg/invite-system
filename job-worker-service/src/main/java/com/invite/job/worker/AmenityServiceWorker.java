package com.invite.job.worker;

import com.invite.job.domain.AmenityVariable;
import com.invite.job.gateway.AmenityServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AmenityServiceWorker {
    AmenityServiceGateway gateway;

    @ZeebeWorker(type = "get-amenity-by-id", autoComplete = true)
    public AmenityVariable handleGetAmenityById(@ZeebeVariable Integer amenityId) {
        return new AmenityVariable(gateway.getAmenity(amenityId.longValue()));
    }
}
