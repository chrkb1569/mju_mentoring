package mju_spring.study.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mju_spring.study.entity.Board;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditResponseDto {
    private String title;
    private String content;

    public BoardEditResponseDto toDto(Board board) {
        return new BoardEditResponseDto(board.getTitle(), board.getContent());
    }
}
