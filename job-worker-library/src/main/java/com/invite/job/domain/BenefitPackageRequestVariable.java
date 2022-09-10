package com.invite.job.domain;

import com.invite.benefit.domain.BenefitPackageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BenefitPackageRequestVariable {
    BenefitPackageRequest benefitPackage;
}
