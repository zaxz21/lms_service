package com.greenart.lms_service.api;

import com.greenart.lms_service.vo.finalGrade.vo.FinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.InsertFinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.MessageVO;
import com.greenart.lms_service.service.AttendService;
import com.greenart.lms_service.service.FinalScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/final")
@RequiredArgsConstructor
@Tag(name = "최종 성적 API", description = "최종 성적 CRU API")
public class FinalAPIController {
    private final FinalScoreService finalScoreService;
    private final AttendService attendService;

    @GetMapping("/{code}")
    @Operation(summary = "최종 성적 조회", description = "강의 코드 입력 시 해당 강의를 듣는 모든 학생들의 모든 성적을 조회합니다.")
    public ResponseEntity<List<FinalScoreVO>> getFinalScore(@Parameter(description = "강의 코드", example = "BAC001-01") @PathVariable String code) {
        return new ResponseEntity<>(finalScoreService.getFinalScore(code), HttpStatus.OK);
    }

    @PostMapping("/{code}")
    @Operation(summary = "최종 성적 입력", description = "강의 코드 와 학번 원하는 성적 입력 시 성적이 부여되며 기존 성적이 있을 시 변경됩니다. 성적은 1(A+)~13(F) 숫자값으로 부여, 0 입력 시 기존 성적 취소")
    public ResponseEntity<MessageVO> insertFinalGrade(@Parameter(description = "강의 코드", example = "BAC001-01") @PathVariable String code, @RequestBody InsertFinalScoreVO data) {
        attendService.putAttendFGrade(code);
        MessageVO response = finalScoreService.insertFinalScore(data, code);
        return new ResponseEntity<>(response, response.getCode());
    }
}
