package com.invite.job.worker;

import com.invite.job.domain.MemberVariable;
import com.invite.job.domain.MembershipVariable;
import com.invite.job.gateway.MemberServiceGateway;
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
public class MemberServiceWorker {
    MemberServiceGateway gateway;

    @ZeebeWorker(type = "get-member-by-id", autoComplete = true)
    public MemberVariable handleGetMemberById(@ZeebeVariable Integer memberId) {
        return new MemberVariable(gateway.getMember(memberId.longValue()));
    }
}
