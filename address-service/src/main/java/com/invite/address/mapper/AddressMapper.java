package com.invite.address.mapper;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.GeoLocation;
import com.invite.address.entity.AddressEntity;
import com.invite.address.entity.GeoLocationEntity;
import com.invite.address.repository.StateRepository;
import com.invite.address.repository.ZipCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
public class AddressMapper {
    ZipCodeMapper mapper;
    StateRepository stateRepository;
    ZipCodeRepository zipCodeRepository;

    public Address toDomain(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .line1(entity.getLine1())
                .line2(entity.getLine2())
                .city(entity.getCity())
                .state(entity.getState().getAbbreviation())
                .zipCode(mapper.toDomain(entity.getZipCode()))
                .geoLocation(GeoLocation.builder()
                        .latitude(entity.getGeoLocation().getLatitude())
                        .longitude(entity.getGeoLocation().getLongitude())
                        .build())
                .build();
    }

    public AddressEntity toEntity(AddressRequest addressRequest, GeoLocation geoLocation) {
        return toEntity(addressRequest, new AddressEntity(), geoLocation);
    }

    public AddressEntity toEntity(AddressRequest addressRequest, AddressEntity target, GeoLocation geoLocation) {
        target.setLine1(addressRequest.getLine1());
        target.setLine2(addressRequest.getLine2());
        target.setCity(addressRequest.getCity());
        target.setState(stateRepository.getReferenceById(addressRequest.getState()));
        target.setZipCode(zipCodeRepository.findByPostalCodeAndPlusFour(addressRequest.getZipCode().getPostalCode(), addressRequest.getZipCode().getPlusFour()));
        target.setGeoLocation(GeoLocationEntity.builder()
                .latitude(geoLocation.getLatitude())
                .longitude(geoLocation.getLongitude())
                .build());
        return target;
    }
}
