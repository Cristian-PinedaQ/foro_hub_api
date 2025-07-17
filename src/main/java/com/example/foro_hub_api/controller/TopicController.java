package com.example.foro_hub_api.controller;

import com.example.foro_hub_api.controller.dto.TopicsDto;
import com.example.foro_hub_api.domain.topic.ITopicRepository;
import com.example.foro_hub_api.domain.topic.Topics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private ITopicRepository repository;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<TopicsDto> list = repository.findAll().stream()
                .map(t -> new TopicsDto(t.getId(), t.getTitulo(),t.getMensaje(),t.getAutor(),t.getCurso()))
                .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid TopicsDto topicsDto, UriComponentsBuilder uriComponentsBuilder){
        Topics topics = (Topics) repository.save(Topics.builder()
                        .id(topicsDto.id())
                        .titulo(topicsDto.titulo())
                        .mensaje(topicsDto.mensaje())
                        .curso(topicsDto.curso())
                        .autor(topicsDto.autor()).build());

        URI uri= uriComponentsBuilder.path("/topicos/save/{id}").buildAndExpand(topics.getId()).toUri();

        return ResponseEntity.created(uri).body(topicsDto);
    }
}
