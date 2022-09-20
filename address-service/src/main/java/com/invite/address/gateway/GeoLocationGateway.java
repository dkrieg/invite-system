package com.invite.address.gateway;

import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.Distance;
import com.invite.address.domain.GeoLocation;

public interface GeoLocationGateway {
    GeoLocation getGeoLocation(AddressRequest addressRequest);

    Distance calculateDistance(GeoLocation start, GeoLocation end);
}
