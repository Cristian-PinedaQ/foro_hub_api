package com.example.foro_hub_api.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topics, Long> {
}
