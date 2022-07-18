package mju_spring.study.controller;

import lombok.RequiredArgsConstructor;
import mju_spring.study.entity.Board;
import mju_spring.study.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller 는 클라이언트(사용자)의 요청을 받는 클래스입니다.
 * Controller에서는 서비스 클래스 (기능 구현된 클래스) 를 불러서 사용자의 요청을 처리합니다.
 *
 * @Controller 는 템플릿 엔진(JSP, Thymeleaf 등등) 사용할 때 주로 쓰이고,
 * @RestController 는 API 서버를 만들 때 주로 사용됩니다. 저희는 API 서버를 만드니 RestController 로 진행합니다.
 */

@RequiredArgsConstructor // 생성자
@RestController
public class BoardController {
    final private BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<?> getBoards() {
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<?> save(@RequestBody Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<?> editBoard(@PathVariable Long id, @RequestBody Board board) {
        return new ResponseEntity<>(boardService.editBoard(id, board), HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("삭제 완료!!", HttpStatus.OK);
    }
}


