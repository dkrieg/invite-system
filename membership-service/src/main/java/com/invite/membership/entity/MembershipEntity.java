package com.invite.membership.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "MEMBERSHIP")
public class MembershipEntity {
    @Id
    @GeneratedValue
    Long id;
    String loginId;
    Long memberId;
    Long benefitPackageId;
    Long homeClubId;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    MembershipLevelEntity level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MembershipEntity that = (MembershipEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("memberId", memberId)
                .toString();
    }
}
