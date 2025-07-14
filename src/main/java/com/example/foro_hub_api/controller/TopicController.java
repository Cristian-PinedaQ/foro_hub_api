package com.example.foro_hub_api.controller;

import com.example.foro_hub_api.controller.dto.TopicsDto;
import com.example.foro_hub_api.domain.topic.ITopicRepository;
import com.example.foro_hub_api.domain.topic.Topics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private ITopicRepository repository;

    @PostMapping("/save")
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid TopicsDto topicsDto, UriComponentsBuilder uriComponentsBuilder){
        Topics topics = (Topics) repository.save(Topics.builder()
                .titulo(topicsDto.titulo())
                .mensaje(topicsDto.mensaje())
                .curso(topicsDto.curso())
                .autor(topicsDto.autor()).build());

        URI uri= uriComponentsBuilder.path("/topicos/save/{id}").buildAndExpand(topics.getId()).toUri();

        return ResponseEntity.created(uri).body(topicsDto);
    }
}
