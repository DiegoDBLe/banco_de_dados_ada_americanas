package br.com.postgres.americanas.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    private Integer id;
    private String nome;
    @Column(name = "dt_nascimento")
    private Date dataNascimento;
    private String cpf;
    private String sexo;
    private String email;
    private String telefone;
    @OneToOne(mappedBy ="aluno", cascade = CascadeType.ALL)
    private Endereco endereco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Aluno aluno = (Aluno) o;
        return getId() != null && Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
/* ORM (Objet relaitur) forma simples de mapear um objeto
 Hibernate é o ORM. O java usa o springData que roda por baixo dos panos hibernate
 ORM é o conceito do freamework que implementa  JPA, e O JPA é a especificação do java.
 */
