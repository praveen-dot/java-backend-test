package com.tui.proof.ws.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value="sequence_generator")
public class SequenceGenerator
{
    @Id
    @EqualsAndHashCode.Include
    private String sequenceName;
    private Long sequenceId;
}
