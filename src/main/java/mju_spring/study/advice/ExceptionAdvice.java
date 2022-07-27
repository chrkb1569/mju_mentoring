package mju_spring.study.advice;

import mju_spring.study.exception.BoardNotFoundException;
import mju_spring.study.exception.GetWrongInfoException;
import mju_spring.study.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return Response.failure(404, "해당 게시글은 존재하지 않습니다.");
    }

    @ExceptionHandler(GetWrongInfoException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response getWrongInfoException() {
        return Response.failure(406, "게시글의 제목, 내용, 작성자를 다시 한 번 확인해주세요");
    }
}
