package com.restdocs.domain.crud.controller;

import com.restdocs.domain.crud.dto.ExampleDto;
import com.restdocs.domain.crud.service.CrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link CrudController} 기본 crud
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/crud")
public class CrudController {

    private final CrudService crudService;

    @GetMapping("/read/{id}")
    private ResponseEntity<ModelMap> getExample (@PathVariable("id") Integer id){
        return crudService.getExample(id);
    }

    @GetMapping("/read")
    private ResponseEntity<List<ExampleDto.Result>> getExampleAll (){
        ResponseEntity<List<ExampleDto.Result>> result = crudService.getExampleAll();
        return result;
    }

    @PostMapping("/create")
    private ResponseEntity setExample(@Valid @RequestBody ExampleDto.Request request){
        return crudService.setExample(request);
    }
}
