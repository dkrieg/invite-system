package com.invite.member.mapper;

import com.invite.address.domain.Address;
import com.invite.member.domain.Member;
import com.invite.member.domain.MemberRequest;
import com.invite.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MemberMapper {

    public Member toDomain(MemberEntity entity, Address address) {
        return Member.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .homeAddress(address)
                .build();
    }

    public MemberEntity toEntity(MemberRequest request, Address address) {
        return toEntity(new MemberEntity(), request, address);
    }

    public MemberEntity toEntity(MemberEntity entity, MemberRequest request, Address address) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setAddressId(address.getId());
        return entity;
    }
}
