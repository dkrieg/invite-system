package com.invite.member.service;

import com.invite.address.domain.Address;
import com.invite.member.gateway.AddressGateway;
import com.invite.member.domain.Member;
import com.invite.member.domain.MemberRequest;
import com.invite.member.entity.MemberEntity;
import com.invite.member.mapper.MemberMapper;
import com.invite.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class MemberDomainServiceImpl implements MemberDomainService {
    MemberRepository repository;
    MemberMapper mapper;
    AddressGateway gateway;

    @Override
    public Collection<Member> fetchAll() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.toDomain(entity, gateway.getAddress(entity.getAddressId())))
                .collect(Collectors.toList());
    }

    @Override
    public Member create(MemberRequest request) {
        return Optional.ofNullable(request)
                .map(r -> {
                    Address address = gateway.createAddress(r.getHomeAddress());
                    MemberEntity entity = mapper.toEntity(r, address);
                    return Map.entry(entity, address);
                })
                .map(e -> {
                    Address address = e.getValue();
                    try {
                        MemberEntity entity = repository.saveAndFlush(e.getKey());
                        return Map.entry(entity, address);
                    } catch (PersistenceException ex) {
                        gateway.deleteAddress(address.getId());
                        throw ex;
                    }
                })
                .map(e -> mapper.toDomain(e.getKey(), e.getValue()))
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Member> fetchById(Long id) {
        return repository.findById(id).map(e -> mapper.toDomain(e, gateway.getAddress(e.getAddressId())));
    }

    @Override
    public Optional<Member> updateById(Long id, MemberRequest request) {
        return repository.findById(id)
                .map(e -> {
                    Address address = gateway.updateAddress(e.getAddressId(), request.getHomeAddress());
                    MemberEntity entity = mapper.toEntity(e, request, address);
                    return Map.entry(entity, address);
                })
                .map(e -> Map.entry(repository.saveAndFlush(e.getKey()), e.getValue()))
                .map(e -> mapper.toDomain(e.getKey(), e.getValue()));
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            gateway.deleteAddress(repository.getReferenceById(id).getAddressId());
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
