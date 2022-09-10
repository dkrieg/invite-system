package com.invite.job.worker;

import com.invite.job.domain.MembershipRequestVariable;
import com.invite.job.domain.MembershipVariable;
import com.invite.job.gateway.MembershipServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MembershipServiceWorker {
    MembershipServiceGateway gateway;

    @ZeebeWorker(type = "create-membership", autoComplete = true)
    public MembershipVariable handleCreateMembership(@ZeebeVariablesAsType MembershipRequestVariable variable) {
        return new MembershipVariable(gateway.createMembership(variable.getMembership()));
    }
}
