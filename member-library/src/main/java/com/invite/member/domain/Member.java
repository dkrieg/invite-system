package com.invite.member.domain;

import com.invite.address.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Member {
    @NotNull
    Long id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    String email;
    @NotNull
    Address homeAddress;
}
