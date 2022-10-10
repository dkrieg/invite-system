package com.invite.club.mapper;

import com.invite.address.domain.Address;
import com.invite.amenity.domain.Amenity;
import com.invite.benefit.domain.BenefitPackage;
import com.invite.club.domain.Club;
import com.invite.club.domain.ClubRequest;
import com.invite.club.entity.ClubAmenityEntity;
import com.invite.club.entity.ClubBenefitPackageEntity;
import com.invite.club.entity.ClubCommunityEntity;
import com.invite.club.entity.ClubEntity;
import com.invite.club.entity.ClubLineOfBusinessEntity;
import com.invite.club.entity.ClubMarketEntity;
import com.invite.club.entity.ClubProviderGroupEntity;
import com.invite.club.repository.ClubAmenityRepository;
import com.invite.club.repository.ClubBenefitPackageRepository;
import com.invite.club.repository.ClubCommunityRepository;
import com.invite.club.repository.ClubLineOfBusinessRepository;
import com.invite.club.repository.ClubMarketRepository;
import com.invite.club.repository.ClubProviderGroupRepository;
import com.invite.club.repository.ClubSegmentRepository;
import com.invite.community.domain.Community;
import com.invite.line.domain.LineOfBusiness;
import com.invite.market.domain.Market;
import com.invite.provider.group.domain.ProviderGroup;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ClubMapper {
    ClubAmenityRepository amenityRepository;
    ClubBenefitPackageRepository benefitPackageRepository;
    ClubMarketRepository marketRepository;
    ClubCommunityRepository communityRepository;
    ClubProviderGroupRepository providerGroupRepository;
    ClubLineOfBusinessRepository lineOfBusinessRepository;
    ClubSegmentRepository segmentRepository;
    ClubSegmentMapper segmentMapper;

    public Club toDomain(ClubEntity entity, Address address, List<Amenity> amenities, List<BenefitPackage> benefitPackages, List<Market> markets, List<ProviderGroup> providerGroups, List<Community> communities, LineOfBusiness lineOfBusiness) {
        return Club.builder()
                .id(entity.getId())
                .name(entity.getName())
                .website(entity.getWebsite())
                .phoneNumber(entity.getPhoneNumber())
                .isOwned(entity.isOwned())
                .isAlliance(entity.isAlliance())
                .isActive(entity.isActive())
                .address(address)
                .amenities(amenities)
                .benefitPackages(benefitPackages)
                .markets(markets)
                .providerGroups(providerGroups)
                .communities(communities)
                .lineOfBusiness(lineOfBusiness)
                .segment(Optional.ofNullable(entity.getSegment()).map(segmentMapper::toDomain).orElse(null))
                .build();
    }

    public ClubEntity toEntity(ClubRequest request, Address address) {
        return toEntity(new ClubEntity(), request, address);
    }

    public ClubEntity toEntity(ClubEntity entity, ClubRequest request, Address address) {
        entity.setName(request.getName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setWebsite(request.getWebsite());
        entity.setActive(request.isActive());
        entity.setAlliance(request.isAlliance());
        entity.setOwned(request.isOwned());
        entity.setAmenities(Optional.ofNullable(request.getAmenityIds())
                .map(l -> l
                        .stream()
                        .map(id -> amenityRepository.findById(id).orElseGet(() -> new ClubAmenityEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setBenefitPackages(Optional.ofNullable(request.getBenefitPackageIds())
                .map(l -> l
                        .stream()
                        .map(id -> benefitPackageRepository.findById(id).orElseGet(() -> new ClubBenefitPackageEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setMarkets(Optional.ofNullable(request.getMarketIds())
                .map(l -> l
                        .stream()
                        .map(id -> marketRepository.findById(id).orElseGet(() -> new ClubMarketEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setProviderGroups(Optional.ofNullable(request.getProviderGroupIds())
                .map(l -> l
                        .stream()
                        .map(id -> providerGroupRepository.findById(id).orElseGet(() -> new ClubProviderGroupEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setCommunities(Optional.ofNullable(request.getCommunityIds())
                .map(l -> l
                        .stream()
                        .map(id -> communityRepository.findById(id).orElseGet(() -> new ClubCommunityEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setLineOfBusiness(Optional.ofNullable(request.getLineOfBusinessId())
                .map(id -> lineOfBusinessRepository.findById(id).orElseGet(() -> new ClubLineOfBusinessEntity(id)))
                .orElse(null));
        entity.setSegment(Optional.ofNullable(request.getSegment()).map(segmentRepository::getReferenceById).orElse(null));
        entity.setAddressId(address.getId());
        return entity;
    }
}
