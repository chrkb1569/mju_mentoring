package mju_spring.study.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardCreateRequestDto {
    // @NotBlank 어노테이션을 통하여 해당 필드에 값이 입력되지 않은 경우에는 오류가 발생함. 이 경우 발생하는 오류가 우리가 추가해주었던
    // MethodArgumentNotValidException 오류 발생
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;
}
