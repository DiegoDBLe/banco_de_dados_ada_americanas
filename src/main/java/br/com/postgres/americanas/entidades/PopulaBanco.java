package br.com.postgres.americanas.entidades;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.Date;
import java.util.Random;

public class PopulaBanco {

    private static String DATA_BASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String DATA_BASE_USER = "admin";
    private static String DATA_BASE_PASSWORD = "admin";
    public static void main(String[] args){

        try {
            atribuirEndereco();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void criarAluno(Integer qtdAlunos) throws SQLException {

        Random r = new Random();

        for (int i = 0; i < qtdAlunos; i++){
            Faker faker = new Faker();
            String nome = faker.name().fullName();
            Date dtNascimento = faker.date().birthday();
            String cpf = faker.number().digits(11);
            String sexo  = getSexo(faker);
            String email = nome.toLowerCase()
                    .replaceAll("\\s","").concat("@gmail.com");
            String telefone = faker.number().digits(11);
            int matricula = Integer.parseInt(faker.number().digits(5));

            Connection connection = DriverManager.getConnection(
                    DATA_BASE_URL,
                    DATA_BASE_USER,
                    DATA_BASE_PASSWORD
            );
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO public.aluno " +
                            "(nome, dt_nascimento, cpf, sexo, email, telefone, matricula) " +
                            "values(?,?,?,?,?,?,?)");
            ps.setString(1, nome);
            ps.setDate(2, new java.sql.Date(dtNascimento.getTime()));
            ps.setString(3, cpf);
            ps.setString(4, sexo);
            ps.setString(5, email);
            ps.setString(6, telefone);
            ps.setInt(7, matricula);

            int linhas = ps.executeUpdate();

            System.out.println(linhas);
        }

    }
    public static void atribuirEndereco() throws SQLException {
        Connection connection = DriverManager.getConnection(
                DATA_BASE_URL,
                DATA_BASE_USER,
                DATA_BASE_PASSWORD
        );
        PreparedStatement ps = connection.prepareStatement("select a.id from aluno a inner join endereco e on e.id_aluno " +
                "= a.id where e.logradouro is null;");
        ResultSet rs = ps.executeQuery();

        Faker faker = new Faker();

        while (rs.next()) {
            ps = connection.prepareStatement("INSERT INTO public.endereco\n" +
                    "(logadouro, nome, numero)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?);\n");
            ps.setInt(1, rs.getInt("id"));
            ps.setString(2,faker.address().streetAddress());
            ps.setString(3,"residencial");
            ps.setString(4,String.valueOf(faker.number().numberBetween(0,1000)));
            ps.setString(5,"apto 5");
            ps.setString(6,faker.number().digits(8));
            ps.execute();

        }
    }
    private static String getSexo(Faker faker) {
        return faker.number().randomDigit() > 4 ? "F" : "M";
    }
}
