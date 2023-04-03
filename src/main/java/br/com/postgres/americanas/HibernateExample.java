package br.com.postgres.americanas;

import br.com.postgres.americanas.entidades.Aluno;
import br.com.postgres.americanas.entidades.Endereco;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateExample {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Aluno.class);
        conf.addAnnotatedClass(Endereco.class);

        SessionFactory sessionFactory = conf.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Aluno");
        List<Aluno> alunos = query.getResultList();

       alunos.forEach(System.out::println);
    }
}
