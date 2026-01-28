package com.mcn.in4.domain.contract.repository;

import com.mcn.in4.domain.contract.entity.CreatorContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<CreatorContract, Long> {

    // 계약 목록 조회 (크리에이터 정보 함께 조회)
    @Query("SELECT DISTINCT c FROM CreatorContract c " +
            "JOIN FETCH c.memberCreator " +
            "ORDER BY c.contractStart DESC")
    List<CreatorContract> findAllContractsWithCreator();

    // 계약 단건 조회
    @Query("SELECT c FROM CreatorContract c " +
            "JOIN FETCH c.memberCreator " +
            "WHERE c.creatorContractId = :contractId")
    Optional<CreatorContract> findContractByIdWithCreator(@Param("contractId") Long contractId);

    // 크리에이터별 계약 조회
    @Query("SELECT c FROM CreatorContract c " +
            "JOIN FETCH c.memberCreator " +
            "WHERE c.memberCreator.memberId = :creatorId " +
            "ORDER BY c.contractStart DESC")
    List<CreatorContract> findContractsByCreatorId(@Param("creatorId") Long creatorId);
}