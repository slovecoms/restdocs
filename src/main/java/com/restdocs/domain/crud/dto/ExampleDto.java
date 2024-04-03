package com.restdocs.domain.crud.dto;

import com.restdocs.domain.crud.entity.Example;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * {@link ExampleDto} 기본 crud
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */

@Getter
public class ExampleDto {

    @Getter @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @Size(min = 1, max = 50)
        @NotNull
        private String title;
        @Size(min = 1, max = 1000)
        @NotNull
        private String content;
        @NotNull
        private LocalDateTime registDttm;

        public Example toEntity(){
            return Example.builder()
                    .title(title)
                    .content(content)
                    .registDttm(LocalDateTime.now())
                    .updateDttm(LocalDateTime.now())
                    .build();
        }
    }

    @Getter @NoArgsConstructor
    public static class Result {
        private Integer id;
        private String title;
        private String content;
        private LocalDateTime registDttm;
        private LocalDateTime updateDttm;

        public Result(Example entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.registDttm = entity.getRegistDttm();
            this.updateDttm = entity.getUpdateDttm();
        }
    }
}
