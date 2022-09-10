package com.invite.job.worker;

import com.invite.job.domain.AmenityRequestVariable;
import com.invite.job.domain.AmenityTypesVariable;
import com.invite.job.domain.AmenityVariable;
import com.invite.job.gateway.AmenityServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
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

    @ZeebeWorker(type = "get-amenity-types", autoComplete = true)
    public AmenityTypesVariable handleGetAmenityTypes() {
        return new AmenityTypesVariable(gateway.getAmenityTypes());
    }

    @ZeebeWorker(type = "create-amenity", autoComplete = true)
    public AmenityVariable handleCreateAmenity(@ZeebeVariablesAsType AmenityRequestVariable variable) {
        return new AmenityVariable(gateway.createAmenity(variable.getAmenity()));
    }
}
