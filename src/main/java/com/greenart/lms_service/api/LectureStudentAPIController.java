package com.greenart.lms_service.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.lms_service.service.LectureStudentService;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDaoVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "내 강의 수강생 정보조회", description = "내 강의 수강생 정보조회")
@RestController
@RequestMapping("/api/lec")
// http://localhost:8520/api/lec/{crLiSeq}
@RequiredArgsConstructor
public class LectureStudentAPIController {
    private final LectureStudentService lecStuService;

    // 내 강의 수강생 조회
    @Operation(summary = "수강생 조회", description = "liSeq 강의번호")
    @GetMapping("/{liSeq}") // liSeq다 맞추기
    public ResponseEntity<List<LectureStudentDaoVO>> getlectStuList(
        @Parameter(description = "강의번호 ex crLiSeq:1")
        @PathVariable Long liSeq) {
        return new ResponseEntity<>(lecStuService.getLectureStudentList(liSeq), HttpStatus.OK);
    }

    // 내 강의 수강생 검색
    @Operation(summary = "수강생 검색", description = "liSeq 강의번호, stuName 학생이름, 검색")
    @GetMapping("/{liSeq}/search/{stuName}")
    public ResponseEntity<List<LectureStudentDaoVO>> getSearchLectureStudent(
        @Parameter(description = "강의번호 ex liSeq:1")
        @PathVariable Long liSeq,
        @Parameter(description = "학생이름 ex stuName:강백호")
        @PathVariable String stuName) {
        return new ResponseEntity<>(lecStuService.searchLectureStudent(liSeq, stuName), HttpStatus.ACCEPTED);
    }
}
