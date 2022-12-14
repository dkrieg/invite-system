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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "BENEFIT_PACKAGE")
public class BenefitPackageEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
    @ManyToMany(fetch = EAGER)
    @JoinTable(name="BENEFIT_PACKAGE_BENEFITS",
            joinColumns=
            @JoinColumn(name="BENEFIT_PACKAGE_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="BENEFIT_ID", referencedColumnName="ID")
    )
    Set<BenefitEntity> benefits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BenefitPackageEntity that = (BenefitPackageEntity) o;

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
                .append("name", name)
                .append("benefits", benefits)
                .toString();
    }
}
