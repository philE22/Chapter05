package com.rubypaper;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rubypaper.domain.BoardVO;
import com.rubypaper.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK) //생략가능
@AutoConfigureMockMvc //controller 뿐만아니라 Service, Repository도 컨테이너 등록
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void testHello() throws Exception {
        when(boardService.hello("둘리")).thenReturn("Hello : 둘리");

        mockMvc.perform(get("/hello").param("name", "둘리"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello : 둘리"))
                .andDo(print());
    }

//    @Test
//    public void testGetBoard() throws Exception {
//        BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
//        assertThat(board.getWriter()).isEqualTo("테스터");
//        System.out.println("board = " + board);
//    }
}
