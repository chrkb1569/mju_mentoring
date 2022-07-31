package mju_spring.study.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mju_spring.study.entity.Board;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardGetResponseDto {
    private String title;
    private String content;
    private String writer;

    public BoardGetResponseDto toDto(Board board) {
        return new BoardGetResponseDto(board.getTitle(), board.getContent(), board.getWriter());
    }
}
