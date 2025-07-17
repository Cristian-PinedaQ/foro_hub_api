package com.example.foro_hub_api.controller;

import com.example.foro_hub_api.controller.dto.TopicsDto;
import com.example.foro_hub_api.domain.topic.ITopicRepository;
import com.example.foro_hub_api.domain.topic.Topics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private ITopicRepository repository;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Topics t = repository.getReferenceById(id);

            TopicsDto topicsDto = new TopicsDto(t.getId(), t.getTitulo(),t.getMensaje(),t.getAutor(),t.getCurso());
            return ResponseEntity.ok(topicsDto);
    }

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

    @Transactional
    @PatchMapping("/patch/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id, @RequestBody TopicsDto topicsDto){
        Optional<Topics> topicsOptional = repository.findById(id);
        if (topicsOptional.isPresent()){
            Topics t = topicsOptional.get();
            if (topicsDto.titulo() != null){
                t.setTitulo(topicsDto.titulo());
            }
            if (topicsDto.mensaje() != null){
                t.setMensaje(topicsDto.mensaje());
            }
            if (topicsDto.autor() != null){
                t.setAutor(topicsDto.autor());
            }
            if (topicsDto.curso() != null){
                t.setCurso(topicsDto.curso());
            }

            return ResponseEntity.ok(topicsDto);
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
