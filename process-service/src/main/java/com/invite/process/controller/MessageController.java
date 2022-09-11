package com.invite.process.controller;

import com.invite.amenity.domain.AmenityRequest;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.job.domain.AmenityRequestVariable;
import com.invite.job.domain.BenefitPackageRequestVariable;
import com.invite.job.domain.MemberRequestVariable;
import com.invite.job.domain.MembershipRequestVariable;
import com.invite.job.domain.OrganizationRequestVariable;
import com.invite.member.domain.MemberRequest;
import com.invite.membership.domain.MembershipRequest;
import com.invite.organization.domain.OrganizationRequest;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/submit")
public class MessageController {
    ZeebeClient client;

    @PostMapping("/new-organization")
    public void newOrganizationSubmitted(@RequestBody OrganizationRequest request) {
        client.newPublishMessageCommand()
                .messageName("newOrganizationSubmitted")
                .correlationKey("new-organization-submitted")
                .variables(new OrganizationRequestVariable(request))
                .send();
    }

    @PostMapping("/new-amenity")
    public void newAmenitySubmitted(@RequestBody AmenityRequest request) {
        client.newPublishMessageCommand()
                .messageName("newAmenityForOrganizationSubmitted")
                .correlationKey("new-amenity-submitted")
                .variables(new AmenityRequestVariable(request))
                .send();
    }

    @PostMapping("/new-benefit-package")
    public void newBenefitPackageSubmitted(@RequestBody BenefitPackageRequest request) {
        client.newPublishMessageCommand()
                .messageName("newBenefitPackageForOrganizationSubmitted")
                .correlationKey("new-benefit-package-submitted")
                .variables(new BenefitPackageRequestVariable(request))
                .send();
    }

    @PostMapping("/new-member")
    public void newMemberSubmitted(@RequestBody MemberRequest request) {
        client.newPublishMessageCommand()
                .messageName("newMemberSubmitted")
                .correlationKey("new-member-submitted")
                .variables(new MemberRequestVariable(request))
                .send();
    }

    @PostMapping("/new-membership")
    public void newMembershipSubmitted(@RequestBody MembershipRequest request) {
        client.newPublishMessageCommand()
                .messageName("newMembershipSubmitted")
                .correlationKey("new-membership-submitted")
                .variables(new MembershipRequestVariable(request))
                .send();
    }
}
