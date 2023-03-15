package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;

public interface AttendInfoMasterRepository extends JpaRepository<AttendInfoMasterEntity, Long>{
    public List<AttendInfoMasterEntity> findByAiMasLiSeq(Long aiMasLiSeq);
}
