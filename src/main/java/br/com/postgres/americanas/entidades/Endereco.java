package br.com.postgres.americanas.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Data
public class Endereco {

    @Id
    private int id;
    private  String logradouro;
    private String nome;
    private  String numero;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aluno", referencedColumnName = "id")
    private Aluno aluno;

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", logadouro='" + logradouro + '\'' +
                ", nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
