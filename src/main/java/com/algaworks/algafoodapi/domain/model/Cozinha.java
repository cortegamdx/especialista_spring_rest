package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;


@JsonRootName("gastronomia")//muda o nome da classe na representacao no formato xml
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
    @EqualsAndHashCode.Include//crie um equals hashcode pra mim apenas usando o id.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @JsonIgnore//remove a propriedade da representacao
   // @JsonProperty("titulo")//nome que vai aparecer na representação
    @Column(nullable = false)
    private String nome;

}
