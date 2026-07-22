package com.autowashpro.repository;

import com.autowashpro.entity.MembershipTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MembershipTierRepository extends JpaRepository<MembershipTier, Integer> {
    Optional<MembershipTier> findByTierName(String tierName);
    Optional<MembershipTier> findByTierNameIgnoreCase(String tierName);
    Optional<MembershipTier> findFirstByMinPointLessThanEqualOrderByMinPointDesc(Integer totalPoint);
}
