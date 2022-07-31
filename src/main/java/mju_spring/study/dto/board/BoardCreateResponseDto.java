package mju_spring.study.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mju_spring.study.entity.Board;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardCreateResponseDto {
    private String title;
    private String content;
    private String writer;

    // Dto 클래스를 통하여 민감한 정보를 가리고, 원하는 필드만을 클라이언트에게 전달할 수 있게됨.
    public BoardCreateResponseDto toDto(Board board) {
        return new BoardCreateResponseDto(board.getTitle(), board.getContent(), board.getWriter());
    }
}
