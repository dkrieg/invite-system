package com.invite.job.worker;

import com.invite.job.gateway.AddressServiceGateway;
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
public class AddressServiceWorker {
    AddressServiceGateway gateway;

    @ZeebeWorker(type = "get-states", autoComplete = true)
    public Map<String, Object> handleGetAmenityTypes() {
        return Map.of("states", gateway.getStates());
    }
}
