package com.invite.address.service;

import com.invite.address.domain.ZipCode;

import java.util.Set;

public interface ZipCodeService {

    Set<ZipCode> fetchByState(String state);
}
