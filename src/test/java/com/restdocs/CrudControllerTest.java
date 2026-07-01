package com.restdocs;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.restdocs.domain.crud.controller.CrudController;
import com.restdocs.domain.crud.dto.ExampleDto;
import com.restdocs.domain.crud.entity.Example;
import com.restdocs.domain.crud.repository.CrudRepository;
import com.restdocs.domain.crud.service.CrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.*;

/**
 * {@link CrudControllerTest} 기본 crud 테스트코드
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */

@WebMvcTest(controllers = CrudController.class)
@AutoConfigureRestDocs
public class CrudControllerTest extends AbstractRestDocsTest{

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrudService crudService;

    @MockBean
    private CrudRepository crudRepository;

    @BeforeEach
    void clean(){
        crudRepository.deleteAll();
    }

    @Test
    @DisplayName("/crud/read 요청 시 DB값 조회")
    void getExampleAll() throws Exception {
        Example example1 = Example.builder()
                .id(1)
                .title("제목1")
                .content("내용1")
                .registDttm(LocalDateTime.now().minusDays(1))
                .updateDttm(LocalDateTime.now())
                .build();
        Example example2 = Example.builder()
                .id(2)
                .title("제목2")
                .content("내용2")
                .registDttm(LocalDateTime.now().minusDays(2))
                .updateDttm(LocalDateTime.now().minusDays(1))
                .build();
        List<Example> exampleList = Arrays.asList(example1, example2);
        Map<String, Object> map = new HashMap<>();
        map.put("exampleList", exampleList);
        when(crudRepository.findAll()).thenReturn(exampleList);
        System.out.println(exampleList.get(0).toString());

        ResultActions actions = mockMvc.perform(get("/crud/read").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exampleList)));

        actions
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getExample() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/crud/read/{id}", 1))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("example 고유번호")
                        )
                ))
                .andReturn();
    }

    @Test
    void setExample() throws Exception {
        // given
        ExampleDto.Request request = new ExampleDto.Request("제목1", "내용1", LocalDateTime.now());
        String json = objectMapper.writeValueAsString(request);

        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/crud/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then
        actions.andExpect(status().isOk());
    }
}
