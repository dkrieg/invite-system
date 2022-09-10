package com.invite.job.worker;

import com.invite.job.domain.CommunitiesVariable;
import com.invite.job.domain.MarketsVariable;
import com.invite.job.domain.OrganizationRequestVariable;
import com.invite.job.domain.OrganizationVariable;
import com.invite.job.domain.ProviderGroupsVariable;
import com.invite.job.gateway.OrganizationServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrganizationServiceWorker {
    OrganizationServiceGateway gateway;

    @ZeebeWorker(type = "get-communities", autoComplete = true)
    public CommunitiesVariable handleGetCommunities() {
        return new CommunitiesVariable(gateway.getCommunities());
    }

    @ZeebeWorker(type = "get-markets", autoComplete = true)
    public MarketsVariable handleGetMarkets() {
        return new MarketsVariable(gateway.getMarkets());
    }

    @ZeebeWorker(type = "get-provider-groups", autoComplete = true)
    public ProviderGroupsVariable handleGetProviderGroups() {
        return new ProviderGroupsVariable(gateway.getProviderGroups());
    }

    @ZeebeWorker(type = "create-organization", autoComplete = true)
    public OrganizationVariable handleCreateOrganization(@ZeebeVariablesAsType OrganizationRequestVariable variable) {
        return new OrganizationVariable(gateway.createOrganization(variable.getOrganization()));
    }
}
