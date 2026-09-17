package com.example.ac1_2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1_2.model.Diretor;
import com.example.ac1_2.model.Filme;
import com.example.ac1_2.repository.DiretorRepository;
import com.example.ac1_2.repository.FilmeRepository;

@SpringBootApplication
public class Ac12Application {

    public static void main(String[] args) {
        SpringApplication.run(Ac12Application.class, args);
    }

    @Bean
    CommandLineRunner executar(FilmeRepository filmeRepository,
            DiretorRepository diretorRepository) {

        return args -> {

            // =========================
            // CRIANDO OS DIRETORES
            // =========================
            Diretor diretor1 = new Diretor();
            diretor1.setNome("Christopher Nolan");

            Diretor diretor2 = new Diretor();
            diretor2.setNome("Steven Spielberg");

            diretorRepository.save(diretor1);
            diretorRepository.save(diretor2);

            // =========================
            // CRIANDO OS FILMES
            // =========================
            Filme filme1 = new Filme();
            filme1.setTitulo("Inception");
            filme1.setDuracao(148);

            Filme filme2 = new Filme();
            filme2.setTitulo("Interstellar");
            filme2.setDuracao(169);

            Filme filme3 = new Filme();
            filme3.setTitulo("Jurassic Park");
            filme3.setDuracao(127);

            Filme filme4 = new Filme();
            filme4.setTitulo("Jaws");
            filme4.setDuracao(124);

            // Associando os filmes aos diretores
            diretor1.adicionarFilme(filme1);
            diretor1.adicionarFilme(filme2);

            diretor2.adicionarFilme(filme3);
            diretor2.adicionarFilme(filme4);

            // Salvando os filmes
            filmeRepository.save(filme1);
            filmeRepository.save(filme2);
            filmeRepository.save(filme3);
            filmeRepository.save(filme4);

            // =========================
            // TESTE 1
            // DURAÇÃO MAIOR QUE 140
            // =========================
            System.out.println("\n===== FILMES COM MAIS DE 140 MINUTOS =====");

            List<Filme> filmesLongos
                    = filmeRepository.findByDuracaoGreaterThan(140);

            for (Filme filme : filmesLongos) {
                System.out.println(
                        filme.getTitulo()
                        + " - "
                        + filme.getDuracao()
                        + " minutos"
                );
            }

            // =========================
            // TESTE 2
            // DURAÇÃO MENOR OU IGUAL A 130
            // =========================
            System.out.println("\n===== FILMES COM ATÉ 130 MINUTOS =====");

            List<Filme> filmesCurtos
                    = filmeRepository.findByDuracaoLessThanEqual(130);

            for (Filme filme : filmesCurtos) {
                System.out.println(
                        filme.getTitulo()
                        + " - "
                        + filme.getDuracao()
                        + " minutos"
                );
            }

            // =========================
            // TESTE 3
            // TÍTULO COMEÇANDO COM "IN"
            // =========================
            System.out.println("\n===== FILMES QUE COMEÇAM COM 'In' =====");

            List<Filme> filmesIn
                    = filmeRepository.findByTituloStartingWith("In");

            for (Filme filme : filmesIn) {
                System.out.println(filme.getTitulo());
            }

            // =========================
            // TESTE 4
            // DIRETORES COMEÇANDO COM "Christ"
            // =========================
            System.out.println("\n===== DIRETORES QUE COMEÇAM COM 'Christ' =====");

            List<Diretor> diretores
                    = diretorRepository.findByNomeStartingWith("Christ");

            for (Diretor diretor : diretores) {
                System.out.println(diretor.getNome());
            }

            // =========================
            // TESTE 5
            // FILMES DOS DIRETORES
            // =========================
            System.out.println("\n===== FILMES DOS DIRETORES =====");

            System.out.println("\nDiretor: " + diretor1.getNome());

            for (Filme filme : diretor1.getFilmes()) {
                System.out.println("- " + filme.getTitulo());
            }

            System.out.println("\nDiretor: " + diretor2.getNome());

            for (Filme filme : diretor2.getFilmes()) {
                System.out.println("- " + filme.getTitulo());
            }
        };
    }
}
