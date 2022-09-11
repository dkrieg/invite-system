package com.invite.address.entity;

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
@Table(name = "ADDRESS")
public final class AddressEntity {
    @Id
    @GeneratedValue
    Long id;
    String line1;
    String line2;
    String city;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    StateEntity state;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    ZipCodeEntity zipCode;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("line1", line1)
                .append("line2", line2)
                .append("city", city)
                .append("state", state)
                .append("zipCode", zipCode)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

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
}
