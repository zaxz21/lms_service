package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDaoVO;

public interface LecturestudentdaoRepository extends JpaRepository<LectureStudentDAO, Long>{
    List<LectureStudentDAO> findByProSeqAndCrLiSeq(Long proSeq, Long crLiSeq);

    List<LectureStudentDAO> findByCrLiSeqAndStuNameContains(Long crLiSeq, String stuName);

}
