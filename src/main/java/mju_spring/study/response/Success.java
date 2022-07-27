package mju_spring.study.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// Result 인터페이스를 통하여 다형성을 보장해주기 위함
// 제네릭 클래스로 지정하여 여러 타입의 데이터가 입력될 수 있도록 지정해줌.
public class Success<T> implements Result {
    private T data;
}
