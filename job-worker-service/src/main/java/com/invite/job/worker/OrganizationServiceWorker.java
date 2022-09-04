package com.invite.job.worker;

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
public class OrganizationServiceWorker {
    OrganizationServiceGateway gateway;

    @ZeebeWorker(type = "get-communities", autoComplete = true)
    public Map<String, Object> handleGetCommunities() {
        return Map.of("communities", gateway.getCommunities());
    }

    @ZeebeWorker(type = "get-markets", autoComplete = true)
    public Map<String, Object> handleGetMarkets() {
        return Map.of("markets", gateway.getMarkets());
    }

    @ZeebeWorker(type = "get-provider-groups", autoComplete = true)
    public Map<String, Object> handleGetProviderGroups() {
        return Map.of("providerGroups", gateway.getProviderGroups());
    }

    @ZeebeWorker(type = "create-organization", autoComplete = true)
    public Map<String, Object> handleCreateOrganization(@ZeebeVariable Map<String, Object> organization) {
        return Map.of("organization", gateway.createOrganization(organization));
    }
}
