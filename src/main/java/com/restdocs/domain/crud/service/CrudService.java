package com.restdocs.domain.crud.service;

import com.restdocs.domain.crud.dto.ExampleDto;
import com.restdocs.domain.crud.entity.Example;
import com.restdocs.domain.crud.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link CrudService} 기본 crud
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */


@Service
@RequiredArgsConstructor
public class CrudService {

    private final CrudRepository crudRepository;

    public ResponseEntity<ModelMap> getExample(Integer exampleId){
        Example example = crudRepository.findById(exampleId).orElse(null);
        ModelMap resultMap = new ModelMap();
        if (example != null){
            ExampleDto.Result dto = new ModelMapper().map(example, ExampleDto.Result.class);
            resultMap.addAttribute("data", dto);
        }
        return ResponseEntity.ok(resultMap);
    }

    public ResponseEntity<List<ExampleDto.Result>> getExampleAll(){
        List<Example> exampleList = crudRepository.findAll();
        List<ExampleDto.Result> dtoList = exampleList.stream().map(ExampleDto.Result::new).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @Transactional
    public ResponseEntity setExample(ExampleDto.Request request){
        Example example = request.toEntity();
        Example save = crudRepository.save(example);
        return ResponseEntity.ok(save);
    }
}
