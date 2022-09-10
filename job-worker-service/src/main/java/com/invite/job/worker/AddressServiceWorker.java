package com.invite.job.worker;

import com.invite.job.domain.StatesVariable;
import com.invite.job.gateway.AddressServiceGateway;
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

    @ZeebeWorker(type = "get-states", autoComplete = true)
    public StatesVariable handleGetStates() {
        return new StatesVariable(gateway.getStates());
    }
}
