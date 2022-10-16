package com.invite.benefit.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "BENEFIT")
public class BenefitEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String code;
    String description;

    @ManyToMany(mappedBy = "benefits")
    Set<BenefitPackageEntity> benefitPackages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BenefitEntity that = (BenefitEntity) o;

        return new EqualsBuilder().append(getId(), that.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .toString();
    }
}
