package mju_spring.study.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 오류 발생 시, 출력해 줄 메세지 지정해줌
public class Failure implements Result {
    private String msg;
}
