package com.example.foro_hub_api.domain.topic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topicos")
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String mensaje;
    @Temporal(TemporalType.DATE)  // Indica que es de tipo fecha y hora
    @Column(name = "fecha_creation", nullable = false, updatable = false)
    @CreationTimestamp  // Autogenera la fecha al momento de la creación
    private Date fecha;
    private Boolean status;
    @NotNull
    private String autor;
    @NotNull
    private String curso;

}
