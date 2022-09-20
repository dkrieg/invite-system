package com.invite.address.gateway;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "geo-location")
@FieldDefaults(level = AccessLevel.PACKAGE)
public class GeoLocationGatewayProperties {
    Info forward = new Info();
    Info distance = new Info();
    String key;

    @Data
    @FieldDefaults(level = AccessLevel.PACKAGE)
    class Info {
        String endpoint;
        String host;
    }
}
