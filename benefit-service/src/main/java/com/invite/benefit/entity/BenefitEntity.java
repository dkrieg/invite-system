package com.invite.benefit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
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
@Table(name = "BENEFIT")
public class BenefitEntity {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    Long amenityId;
    @Column(nullable = false)
    String name;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    BenefitTierEntity tier;
}
