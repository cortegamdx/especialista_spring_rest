package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@JsonRootName("cozinhas")
public class CozinhasXMLWrapper {
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)//desabilitando um embrulho
    @NonNull
   private List<Cozinha> cozinhas;
}
