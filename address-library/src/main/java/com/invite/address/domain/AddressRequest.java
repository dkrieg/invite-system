package com.invite.address.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddressRequest implements Serializable {
    @NotBlank
    String line1;
    String line2;
    @NotBlank
    String city;
    @NotBlank
    String state;
    @NotNull
    ZipCode zipCode;
}
