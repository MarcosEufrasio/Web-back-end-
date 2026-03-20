package com.facens.biblioteca_api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.facens.biblioteca_api.model.Livro;
import com.facens.biblioteca_api.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public List<Livro> listar(){
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Livro salvar(Livro livro){
        return repository.save(livro);
    }

    public Livro atualizar(Long id, Livro livro){
        livro.setId(id);
        return repository.save(livro);
    }

    public void remover(Long id){
        repository.deleteById(id);
    }

    public Livro emprestar(Long id){

        Livro livro = repository.findById(id).orElseThrow();

        if(livro.isEmprestado()){
            throw new RuntimeException("Livro já emprestado");
        }

        livro.setEmprestado(true);
        livro.setDataEmprestimo(LocalDate.now());

        return repository.save(livro);
    }

    public Livro devolver(Long id){

        Livro livro = repository.findById(id).orElseThrow();

        if(!livro.isEmprestado()){
            throw new RuntimeException("Livro não está emprestado");
        }

        livro.setEmprestado(false);
        livro.setDataEmprestimo(null);

        return repository.save(livro);
    }

}