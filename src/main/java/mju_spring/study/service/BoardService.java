package mju_spring.study.service;

import lombok.RequiredArgsConstructor;
import mju_spring.study.dto.board.*;
import mju_spring.study.entity.Board;
import mju_spring.study.exception.BoardNotFoundException;
import mju_spring.study.exception.GetWrongInfoException;
import mju_spring.study.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service 는 기능을 구현하는 것에 초점을 둔 클래스입니다.
 * Repository 를 불러와서, 데이터베이스에 데이터를 넣거나 혹은 가져와서 기능을 구현합니다.
 * <p>
 * Service 클래스는 Controller 에서 불러와서 사용합니다.
 */

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<BoardsResponseDto> getBoards() {
        return boardRepository.findAll().stream().map(s-> new BoardsResponseDto().toDto(s)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public BoardGetResponseDto getBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return new BoardGetResponseDto().toDto(board);
    }

    @Transactional
    public BoardCreateResponseDto save(BoardCreateRequestDto requestDto) {
        Board board = new Board(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
        boardRepository.save(board);

        return new BoardCreateResponseDto().toDto(board);
    }

//    @Transactional
//    public Board save(Board board) {
//        if(board.getTitle().equals("") || board.getContent().equals("") || board.getWriter().equals("")) {
//            throw new GetWrongInfoException();
//        }
//
//        return boardRepository.save(board);
//    }

//    @Transactional
//    public Board editBoard(Long id, Board board) {
//        Board findItem = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
//
//        findItem.setTitle(board.getTitle());
//        findItem.setContent(board.getContent());
//        findItem.setWriter(board.getWriter());
//
//        return board;
//    }

    @Transactional
    public BoardEditResponseDto editBoard(Long id, BoardEditRequestDto requestDto) {
        Board findItem = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        findItem.setTitle(requestDto.getTitle());
        findItem.setContent(requestDto.getContent());

        return new BoardEditResponseDto().toDto(findItem);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}

