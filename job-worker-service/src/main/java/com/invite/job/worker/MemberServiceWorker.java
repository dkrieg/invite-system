package com.invite.job.worker;

import com.invite.job.domain.MemberRequestVariable;
import com.invite.job.domain.MemberVariable;
import com.invite.job.gateway.MemberServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
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

    @ZeebeWorker(type = "create-member", autoComplete = true)
    public MemberVariable handleCreateMember(@ZeebeVariablesAsType MemberRequestVariable member) {
        return new MemberVariable(gateway.createMember(member.getMember()));
    }
}
