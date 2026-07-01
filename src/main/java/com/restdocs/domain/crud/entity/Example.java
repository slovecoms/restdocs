package com.restdocs.domain.crud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * {@link Example} 기본 crud
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@DynamicUpdate
@DynamicInsert
@Table(name = "example")
public class Example {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;
    @Column(name = "content", length = 1000, nullable = false)
    private String content;
    @Column(name = "registDttm")
    @CreationTimestamp
    private LocalDateTime registDttm;
    @Column(name = "updateDttm")
    @UpdateTimestamp
    private LocalDateTime updateDttm;

    @Builder
    public Example(
            Integer id,
            String title,
            String content,
            LocalDateTime registDttm,
            LocalDateTime updateDttm
    ){
        this.id = id;
        this.title = title;
        this.content = content;
        this.registDttm = registDttm;
        this.updateDttm = updateDttm;
    }

}
