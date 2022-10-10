package com.invite.club.service;

import com.invite.address.domain.Address;
import com.invite.club.domain.Club;
import com.invite.club.domain.ClubRequest;
import com.invite.club.entity.ClubAmenityEntity;
import com.invite.club.entity.ClubBenefitPackageEntity;
import com.invite.club.entity.ClubCommunityEntity;
import com.invite.club.entity.ClubEntity;
import com.invite.club.entity.ClubMarketEntity;
import com.invite.club.entity.ClubProviderGroupEntity;
import com.invite.club.gateway.AddressServiceGateway;
import com.invite.club.gateway.AmenityServiceGateway;
import com.invite.club.gateway.BenefitServiceGateway;
import com.invite.club.gateway.CommunityServiceGateway;
import com.invite.club.gateway.LineOfBusinessServiceGateway;
import com.invite.club.gateway.MarketServiceGateway;
import com.invite.club.gateway.ProviderGroupServiceGateway;
import com.invite.club.mapper.ClubMapper;
import com.invite.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ClubDomainServiceImpl implements ClubDomainService {
    ClubRepository repository;
    ClubMapper mapper;
    AddressServiceGateway addressServiceGateway;
    AmenityServiceGateway amenityServiceGateway;
    BenefitServiceGateway benefitServiceGateway;
    CommunityServiceGateway communityServiceGateway;
    LineOfBusinessServiceGateway lineOfBusinessServiceGateway;
    MarketServiceGateway marketServiceGateway;
    ProviderGroupServiceGateway providerGroupServiceGateway;

    @Override
    public Collection<Club> fetchAll() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.toDomain(entity,
                        addressServiceGateway.getAddress(entity.getAddressId()),
                        amenityServiceGateway.getAmenitiesByIds(entity.getAmenities().stream().map(ClubAmenityEntity::getId).collect(toList())),
                        benefitServiceGateway.getBenefitPackagesByIds(entity.getBenefitPackages().stream().map(ClubBenefitPackageEntity::getId).collect(toList())),
                        marketServiceGateway.getMarketsByIds(entity.getMarkets().stream().map(ClubMarketEntity::getId).collect(toList())),
                        providerGroupServiceGateway.getProviderGroupsByIds(entity.getProviderGroups().stream().map(ClubProviderGroupEntity::getId).collect(toList())),
                        communityServiceGateway.getCommunitiesByIds(entity.getCommunities().stream().map(ClubCommunityEntity::getId).collect(toList())),
                        lineOfBusinessServiceGateway.getLineOfBusiness(entity.getLineOfBusiness().getId())))
                .collect(toList());
    }

    @Override
    public Club create(ClubRequest request) {
        return Optional.ofNullable(request)
                .map(r -> {
                    Address address = addressServiceGateway.createAddress(r.getAddress());
                    ClubEntity entity = mapper.toEntity(r, address);
                    return Map.entry(entity, address);
                })
                .map(e -> {
                    Address address = e.getValue();
                    try {
                        ClubEntity entity = repository.saveAndFlush(e.getKey());
                        return Map.entry(entity, address);
                    } catch (PersistenceException ex) {
                        addressServiceGateway.deleteAddress(address.getId());
                        throw ex;
                    }
                })
                .map(e -> mapper.toDomain(e.getKey(), e.getValue(),
                        amenityServiceGateway.getAmenitiesByIds(e.getKey().getAmenities().stream().map(ClubAmenityEntity::getId).collect(toList())),
                        benefitServiceGateway.getBenefitPackagesByIds(e.getKey().getBenefitPackages().stream().map(ClubBenefitPackageEntity::getId).collect(toList())),
                        marketServiceGateway.getMarketsByIds(e.getKey().getMarkets().stream().map(ClubMarketEntity::getId).collect(toList())),
                        providerGroupServiceGateway.getProviderGroupsByIds(e.getKey().getProviderGroups().stream().map(ClubProviderGroupEntity::getId).collect(toList())),
                        communityServiceGateway.getCommunitiesByIds(e.getKey().getCommunities().stream().map(ClubCommunityEntity::getId).collect(toList())),
                        lineOfBusinessServiceGateway.getLineOfBusiness(e.getKey().getLineOfBusiness().getId())))
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Club> fetchById(Long id) {
        return repository.findById(id).map(e -> mapper.toDomain(e,
                addressServiceGateway.getAddress(e.getAddressId()),
                amenityServiceGateway.getAmenitiesByIds(e.getAmenities().stream().map(ClubAmenityEntity::getId).collect(toList())),
                benefitServiceGateway.getBenefitPackagesByIds(e.getBenefitPackages().stream().map(ClubBenefitPackageEntity::getId).collect(toList())),
                marketServiceGateway.getMarketsByIds(e.getMarkets().stream().map(ClubMarketEntity::getId).collect(toList())),
                providerGroupServiceGateway.getProviderGroupsByIds(e.getProviderGroups().stream().map(ClubProviderGroupEntity::getId).collect(toList())),
                communityServiceGateway.getCommunitiesByIds(e.getCommunities().stream().map(ClubCommunityEntity::getId).collect(toList())),
                lineOfBusinessServiceGateway.getLineOfBusiness(e.getLineOfBusiness().getId())));
    }

    @Override
    public Optional<Club> fetchByName(String name) {
        return repository.findByName(name).map(e -> mapper.toDomain(e,
                addressServiceGateway.getAddress(e.getAddressId()),
                amenityServiceGateway.getAmenitiesByIds(e.getAmenities().stream().map(ClubAmenityEntity::getId).collect(toList())),
                benefitServiceGateway.getBenefitPackagesByIds(e.getBenefitPackages().stream().map(ClubBenefitPackageEntity::getId).collect(toList())),
                marketServiceGateway.getMarketsByIds(e.getMarkets().stream().map(ClubMarketEntity::getId).collect(toList())),
                providerGroupServiceGateway.getProviderGroupsByIds(e.getProviderGroups().stream().map(ClubProviderGroupEntity::getId).collect(toList())),
                communityServiceGateway.getCommunitiesByIds(e.getCommunities().stream().map(ClubCommunityEntity::getId).collect(toList())),
                lineOfBusinessServiceGateway.getLineOfBusiness(e.getLineOfBusiness().getId())));
    }

    @Override
    public Optional<Club> updateById(Long id, ClubRequest request) {
        return repository.findById(id)
                .map(e -> {
                    Address address = addressServiceGateway.updateAddress(e.getAddressId(), request.getAddress());
                    ClubEntity entity = mapper.toEntity(e, request, address);
                    return Map.entry(entity, address);
                })
                .map(e -> Map.entry(repository.saveAndFlush(e.getKey()), e.getValue()))
                .map(e -> mapper.toDomain(e.getKey(), e.getValue(),
                        amenityServiceGateway.getAmenitiesByIds(e.getKey().getAmenities().stream().map(ClubAmenityEntity::getId).collect(toList())),
                        benefitServiceGateway.getBenefitPackagesByIds(e.getKey().getBenefitPackages().stream().map(ClubBenefitPackageEntity::getId).collect(toList())),
                        marketServiceGateway.getMarketsByIds(e.getKey().getMarkets().stream().map(ClubMarketEntity::getId).collect(toList())),
                        providerGroupServiceGateway.getProviderGroupsByIds(e.getKey().getProviderGroups().stream().map(ClubProviderGroupEntity::getId).collect(toList())),
                        communityServiceGateway.getCommunitiesByIds(e.getKey().getCommunities().stream().map(ClubCommunityEntity::getId).collect(toList())),
                        lineOfBusinessServiceGateway.getLineOfBusiness(e.getKey().getLineOfBusiness().getId())));
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            addressServiceGateway.deleteAddress(repository.getReferenceById(id).getAddressId());
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
