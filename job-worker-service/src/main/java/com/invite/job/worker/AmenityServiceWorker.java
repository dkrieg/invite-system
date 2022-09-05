package com.invite.job.worker;

import com.invite.job.gateway.AmenityServiceGateway;
import com.invite.job.gateway.OrganizationServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AmenityServiceWorker {
    AmenityServiceGateway gateway;

    @ZeebeWorker(type = "get-amenity-types", autoComplete = true)
    public Map<String, Object> handleGetAmenityTypes() {
        return Map.of("amenityTypes", gateway.getAmenityTypes());
    }

    @ZeebeWorker(type = "create-amenity", autoComplete = true)
    public Map<String, Object> handleCreateAmenity(@ZeebeVariable Map<String, Object> amenity) {
        return Map.of("amenity", gateway.createAmenity(amenity));
    }
}
