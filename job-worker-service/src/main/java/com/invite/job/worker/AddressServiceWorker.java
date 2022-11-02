package com.invite.job.worker;

import com.invite.address.domain.Distance;
import com.invite.job.gateway.AddressServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AddressServiceWorker {
    AddressServiceGateway gateway;

    @ZeebeWorker(type = "calculate-distance-between-addresses", autoComplete = true)
    public Distance handleCalculateDistanceBetweenAddresses(@ZeebeVariable Integer startId, @ZeebeVariable Integer endId) {
        return gateway.calculateDistance(startId.longValue(), endId.longValue());
    }
}
