package br.com.postgres.americanas.jdb;

import br.com.postgres.americanas.entidades.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectExample {

    private static String DATA_BASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String DATA_BASE_USER = "admin";
    private static String DATA_BASE_PASSWORD = "admin";
    public static void main(String[] args) throws SQLException {

        List<Aluno> alunos = new ArrayList();

        try {
        Connection connection = DriverManager.getConnection(
                DATA_BASE_URL,
                DATA_BASE_USER,
                DATA_BASE_PASSWORD
        );
            PreparedStatement ps = connection.prepareStatement("select * from public.aluno");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
               alunos.forEach(System.out::println);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
