package com.invite.job.worker;

import com.invite.job.domain.MembershipVariable;
import com.invite.job.gateway.MembershipServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
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

    @ZeebeWorker(type = "get-membership-by-member-id", autoComplete = true)
    public MembershipVariable handleGetMembershipByMemberId(@ZeebeVariable Integer memberId) {
        return new MembershipVariable(gateway.getMembershipByMemberId(memberId.longValue()));
    }
}
