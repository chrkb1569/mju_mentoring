package mju_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mju_spring.study.controller.BoardController;
import mju_spring.study.dto.board.BoardCreateRequestDto;
import mju_spring.study.dto.board.BoardEditRequestDto;
import mju_spring.study.entity.Board;
import mju_spring.study.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TDD 테스트 주도 개발
@ExtendWith(MockitoExtension.class) // Mockito의 Mock 객체를 사용하기 위한 Annotation
// Mockito??
// Mock 객체를 쉽게 만들고, 관리하고, 검증할 수 있는 방법을 제공하는 프레임워크
// Mock: 진짜 객체와 비슷하게 동작하지만, 프로그래머가 직접 행동을 관리하는 객체
public class BoardControllerTest {
    @InjectMocks
    BoardController boardController;

    @Mock
    BoardService boardService;

    // MockMvc??
    // 테스트를 실행할 경우, 실제 서버에 구현한 애플리케이션 즉, 실제 서블릿 컨테이너를 사용하지 않고
    // 테스트용으로 시뮬레이션하여 MVC가 되도록 도와주는 클래스
    // 실제로 네트워크에 연결하지 않고도 API 테스트가 가능하도록 모킹된 객체
    MockMvc mockMvc;

    // ObjectMapper??
    // JSON 형식을 사용할 때, 응답들을 직렬화하고 요청들을 역직렬화 할 때 사용하는 기술

    // 직렬화??
    // 데이터를 전송하거나 저장할 때 바이트 문자열이어야 하기 때문에 객체들을 문자열로 바꾸어 주는 것
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    // @BeforeEach 본 어노테이션을 붙인 메서드는 테스트 메서드 실행 이전에 수행
    // @AfterEach 본 어노테이션을 붙인 메서드는 테스트 메서드 실행 이후에 수행
    // @BeforeAll 본 어노테이션을 붙인 메서드는 해당 테스트 클래스를 초기화할 때 딱 한번 수행되는 메서드
    // @AfterAll 본 어노테이션을 붙인 메서드는 해당 테스트 클래스 내 테스트 메서드를 모두 실행시킨 후 딱 한번 수행되는 메서드
    // @Disabled 본 어노테이션을 붙인 테스트 메서드는 무시
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
        // MockMvcBuiler를 사용해서 파라미터를 반드시 세팅해주어야 하는데 webAppContextSetup 혹은 standaloneSetup 중
        // 하나를 선택하여 세팅해줄 수 있음 "standaloneSetup"은 단위 테스트에 약간 더 가까움.
        // 한 번에 하나의 컨트롤러를 테스트하고, 컨트롤러에 수동으로 모의 종속성을 주입할 수 있으며,
        // 스프링 구성을 로드하는 것도 포함되지 않는다.
        // 이러한 테스트는 스타일에 더 중점을 두고 어떤 컨트롤러가 테스트되고 있는지,
        // 특정 스프링 MVC 구성이 작동해야하는지 등을 쉽게 볼 수 있습니다.
    }

    @Test // 본 어노테이션을 붙이면 Test 메서드로 인식하고 테스트 시행행
    @DisplayName("게시글 작성") // 테스트 실행시, 해당 문자열로 테스트를 식별할 수 있음.
    // @Nested 어노테이션과 종종 같이 사용되기도 함
    // @Nested 어노테이션을 사용하게 될 경우, 테스트를 여러 분할로 쪼갤 수 있음.
    public void saveBoardTest() throws Exception {
        // given
        BoardCreateRequestDto requestDto = new BoardCreateRequestDto("제목", "내용", "홍길동");

        // when, then
        mockMvc.perform( // perform()을 이용하여 설정한 MockMvc를 실행할 수 있음
                        post("/boards") // post방식으로 해당 url에 접근
                                .contentType(MediaType.APPLICATION_JSON) // contentType을 통하여 타입 설정
                                .content(objectMapper.writeValueAsString(requestDto)) // 요청의 본문 설정
                ).andExpect(status().isOk()); // 예상값 검증하는 부분 현재 Controller에서 상태 코드를 2xx로 반환하였으므로,
        // status().isOk()로 기대중임. andExpect() 내부에 다른 상태 코드를 기입한다면 오류 발생함.
        // ex) .andExpect(status().isCreated())

        verify(boardService).save(requestDto);
        // verify를 통하여 괄호 안에 들어가게 되는 mock 객체가 뒤에 붙는 메소드를 실행하였는지 확인하는 듯
        // 해당 테스트 안에서 특정 메소드를 호출했는지에 대해서 검증을 하기 위하여 Mockito에서 지원하는 verify() 메소드 사용
    }

    /*
    * ObjectMapper objectMapper = new ObjectMapper();
    * User user = new User("Ryan", 30);
    * // 문자열 출력
    * String userAsString = objectMapper.writeValueAsString(user); -> {"name":"Ryan","age":30}
    */

    /*
    * param / params : 쿼리 스트링 설정
    * cookie : 쿠키 설정
    * requestAttr : 요청 스코프 객체 설정
    * sessionAttr : 세션 스코프 객체 설정
    * content : 요청 본문 설정
    * header / headers : 요청 헤더 설정
    * contentType : 본문 타입 설정
    *
    * status : 상태 코드 검증
    * header : 응답 header 검증
    * content : 응답 본문 검증
    * cookie : 쿠키 상태 검증
    * view : 컨트롤러가 반환한 뷰 이름 검증
    * redirectedUrl(Pattern) : 리다이렉트 대상의 경로 검증
    * model : 스프링 MVC 모델 상태 검증
    * request : 세션 스코프, 비동기 처리, 요청 스코프 상태 검증
    * forwardedUrl : 이동대상의 경로 검증
    *
    * andDo() : print, log를 사용할 수 있는 메소드
    * print() : 실행결과를 지정해준 대상으로 출력, default = System.out
    * log() : 실행결과를 디버깅 레벨로 출력, 레벨은 org.springframework.test.web.servlet.result
    *
    * isOk() : 상태 코드가 200인지 확인
    * isNotFount() : 404인지 확인
    * isMethodNotAllowed() : 405인지 확인
    * isInternalServerError() : 500인지 확인
    * is(int status) : 임의로 지정한 상태 코드인지 확인
    * */


    @Test
    @DisplayName("전체 게시글 조회")
    public void findBoardsTest() throws Exception {
        // given

        // when, then
        mockMvc.perform(
                        get("/boards"))
                .andExpect(status().isOk());

        verify(boardService).getBoards();
    }


    @Test
    @DisplayName("게시건 단건 조회")
    public void findBoardTest() throws Exception {
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                        get("/board/{id}", id))
                .andExpect(status().isOk());

        verify(boardService).getBoard(id);
    }


    @Test
    @DisplayName("게시글 수정")
    public void editBoardTest() throws Exception {
        // given
        Long id = 1L;
        Board board = new Board(1L, "제목1", "내용1", "작성자");
        BoardEditRequestDto requestDto = new BoardEditRequestDto("제목1", "내용1");

        // when, then
        mockMvc.perform(
                        put("/board/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated());

        verify(boardService).editBoard(id, requestDto);
        assertThat(board.getTitle()).isEqualTo("제목1"); // assertThat()을 활용하여 괄호 안에 값을 받고,
        // 해당 값을 통하여 비교하는 것.
    }


    @Test
    @DisplayName("게시글 삭제")
    public void deleteBoardTest() throws Exception {
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                        delete("/board/{id}", id))
                .andExpect(status().isOk());

        verify(boardService).deleteBoard(id);
    }
}
