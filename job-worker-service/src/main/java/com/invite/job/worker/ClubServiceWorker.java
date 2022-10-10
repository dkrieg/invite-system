package com.invite.job.worker;

import com.invite.job.domain.ClubVariable;
import com.invite.job.gateway.ClubServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ClubServiceWorker {
    ClubServiceGateway gateway;

    @ZeebeWorker(type = "get-club-by-id", autoComplete = true)
    public ClubVariable handleGetClubById(@ZeebeVariable Integer clubId) {
        return new ClubVariable(gateway.getClub(clubId.longValue()));
    }
}
