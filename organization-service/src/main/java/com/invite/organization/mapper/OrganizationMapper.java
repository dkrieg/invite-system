package com.invite.organization.mapper;

import com.invite.address.domain.Address;
import com.invite.organization.domain.Organization;
import com.invite.organization.domain.OrganizationRequest;
import com.invite.organization.entity.OrganizationEntity;
import com.invite.organization.repository.CommunityRepository;
import com.invite.organization.repository.OrganizationSegmentRepository;
import com.invite.organization.repository.ProviderGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrganizationMapper {
    CommunityMapper communityMapper;
    CommunityRepository communityRepository;
    ProviderGroupMapper providerGroupMapper;
    ProviderGroupRepository providerGroupRepository;
    OrganizationSegmentRepository organizationSegmentRepository;
    OrganizationSegmentMapper organizationSegmentMapper;

    public Organization toDomain(OrganizationEntity entity, Address address) {
        return Organization.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(address)
                .community(Optional.ofNullable(entity.getCommunity()).map(communityMapper::toDomain).orElse(null))
                .providerGroup(Optional.ofNullable(entity.getProviderGroup()).map(providerGroupMapper::toDomain).orElse(null))
                .segment(Optional.ofNullable(entity.getSegment()).map(organizationSegmentMapper::toDomain).orElse(null))
                .build();
    }

    public OrganizationEntity toEntity(OrganizationRequest request, Address address) {
        return toEntity(new OrganizationEntity(), request, address);
    }

    public OrganizationEntity toEntity(OrganizationEntity entity, OrganizationRequest request, Address address) {
        entity.setName(request.getName());
        entity.setCommunity(Optional.ofNullable(request.getCommunityId()).map(communityRepository::getReferenceById).orElse(null));
        entity.setProviderGroup(Optional.ofNullable(request.getProviderGroupId()).map(providerGroupRepository::getReferenceById).orElse(null));
        entity.setSegment(Optional.ofNullable(request.getSegment()).map(organizationSegmentRepository::getReferenceById).orElse(null));
        entity.setAddressId(address.getId());
        return entity;
    }
}
