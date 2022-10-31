package com.invite.job.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class RoundsRestriction {
    int maxRounds;
    RoundsPerPeriod maxRoundsPerPeriod;
    RestrictionAppliesTo appliesTo;
    String declineReason;
}
