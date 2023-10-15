package com.example.wanted.controller;

import com.example.wanted.dto.req.ApplayReq;
import com.example.wanted.dto.req.RegistRecuritReq;
import com.example.wanted.dto.req.UpdateRecuritReq;
import com.example.wanted.dto.res.RecuritDetailReq;
import com.example.wanted.dto.res.RecuritReq;
import com.example.wanted.dto.res.ResponseDto;
import com.example.wanted.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/wanted")
@RequiredArgsConstructor
@CrossOrigin("*")
public class wantedController {

    private final RecruitmentService recruitmentService;

    /*
    1.채용공고 등록 201 Created 409 Conmflict 500 에러 발생
    2.채용공고 수정 200 OK 404 Not Found
    3.채용공고 삭제 200 OK 404 Not Found
    4.채용공고 목록 200 OK 404 Not Found
    4-1. 채용공고 검색 기능 구현 200 OK 404 Not Found
    5.채용 상세 페이지 200 OK 404 Not Found
    6.채용공고에 지원  200 OK 409 Conflict
     */


    //채용공고 등록
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody RegistRecuritReq recuritReq){
        try{
            recruitmentService.registRecruitment(recuritReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "채용공고 등록 완료", null));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }

    //채용공고 수정
    @PutMapping("/{recruitment_id}")
    public ResponseEntity<?> update(@PathVariable("recruitment_id") Long recruitmentId, @RequestBody UpdateRecuritReq updateRecuritReq){
        try{
            recruitmentService.updateRecruitment(recruitmentId, updateRecuritReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고 수정 완료", null));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }

    //채용공고 삭제
    @DeleteMapping("/{recruitment_id}")
    public ResponseEntity<?> delete(@PathVariable("recruitment_id") Long recruitmentId){
        try{
            recruitmentService.deleteRecruitment(recruitmentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고 삭제 완료", null));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }

    //채용공고 목록
    @GetMapping("")
    public ResponseEntity<?> list(){
        try{
            List<RecuritReq> recuritReqs = recruitmentService.listRecruitment();
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고 목록 불러오기 완료", recuritReqs));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }

    //채용공고 검색 기능
    @GetMapping("/search={word}")
    public ResponseEntity<?> search(@PathVariable("word") String word){
        try{
            List<RecuritReq> recuritReqs = recruitmentService.searchRecruitment(word);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고 검색 완료", recuritReqs));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }

    //채용상세 페이지
    @GetMapping("/{recruitment_id}")
    public ResponseEntity<?> select(@PathVariable("recruitment_id") Long recruitmentId){
        try{
            RecuritDetailReq recruitment = recruitmentService.getRecruitment(recruitmentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고 상세 페이지 불러오기 완료", recruitment));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }
    
    //채용공고에 지원
    @PostMapping("/apply")
    public ResponseEntity<?> select(@RequestBody ApplayReq applayReq){
        try{
            recruitmentService.apply(applayReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.OK.toString(), "채용공고에 지원 완료! ",  null));
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CONFLICT.toString(), "같은 채용공고에 중복 지원을 불가합니다!", null));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.NOT_FOUND.toString(), e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "서버 내부 에러 발생", null));
        }
    }
}
