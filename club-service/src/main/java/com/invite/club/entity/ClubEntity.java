package com.invite.club.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "CLUB")
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
    String phoneNumber;
    String website;
    boolean isActive;
    boolean isOwned;
    boolean isAlliance;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "CLUB_MARKET_CLUBS",
            joinColumns =
            @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "MARKET_ID", referencedColumnName = "ID")
    )
    Set<ClubMarketEntity> markets;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "CLUB_COMMUNITY_CLUBS",
            joinColumns =
            @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "COMMUNITY_ID", referencedColumnName = "ID")
    )
    Set<ClubCommunityEntity> communities;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "CLUB_PROVIDER_GROUP_CLUBS",
            joinColumns =
            @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "PROVIDER_GROUP_ID", referencedColumnName = "ID")
    )
    Set<ClubProviderGroupEntity> providerGroups;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "CLUB_AMENITY_CLUBS",
            joinColumns =
            @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "AMENITY_ID", referencedColumnName = "ID")
    )
    Set<ClubAmenityEntity> amenities;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "CLUB_BENEFIT_PACKAGE_CLUBS",
            joinColumns =
            @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "BENEFIT_PACKAGE_ID", referencedColumnName = "ID")
    )
    Set<ClubBenefitPackageEntity> benefitPackages;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn
    ClubLineOfBusinessEntity lineOfBusiness;

    @ManyToOne
    @JoinColumn
    ClubSegmentEntity segment;

    Long addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ClubEntity that = (ClubEntity) o;

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
                .append("name", name)
                .append("markets", markets)
                .append("communities", communities)
                .append("providerGroup", providerGroups)
                .append("addressId", addressId)
                .toString();
    }
}
