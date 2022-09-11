package com.invite.job.domain;

import com.invite.organization.domain.OrganizationSegment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrganizationSegmentsVariable {
    List<OrganizationSegment> organizationSegments;
}
