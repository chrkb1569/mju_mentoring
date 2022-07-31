package mju_spring.study.advice;

import mju_spring.study.exception.BoardNotFoundException;
import mju_spring.study.exception.GetWrongInfoException;
import mju_spring.study.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    // @Valid 어노테이션을 선언한 뒤에, 올바르지 않은 파라미터 값이 들어올 경우에 해당 오류가 터짐.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 해당 오류가 발생할 경우 상태코드는 400이라고 함. Bad Request
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    //BindingResult 의 경우 매개 변수를 Bean에 Binding 해줄 때, 발생하는 오류를 받기 위하여 선언해야하는 어노테이션이라고 함.
    //쉽게 생각해서, 매개변수를 가져와서 Bean을 생성할 때, 발생하는 오류를 처리해주는 인터페이스인것 같음.
    //getFieldError()로 필드에서 발생한 오류들을 가져온다음에 getDefaultMessage()를 통해 메세지를 가져오는 듯
}
