package br.com.unimedceara.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONFIG_CORE", schema = "UNMVMCARD")
public class ConfigurationCore {

    @Id
    @Column(name = "CHAVE")
    String chave;

    @Column(name = "VALOR")
    String valor;
}
