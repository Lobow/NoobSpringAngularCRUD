package com.example.productcrud.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.productcrud.model.Produto;
import com.example.productcrud.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProdutoRest {
	
	public ProdutoRest() {
		System.out.println("Chamou o controlador");
	}
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> get(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Produto produto) {
	   return produtoRepository.findById(id)
	           .map(record -> {
	               record.setNome(produto.getNome());
	               record.setValor(produto.getValor());
	               Produto produto = produtoRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public void post(@RequestBody Produto produto) {
			produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		produtoRepository.deleteById(id);
	}
	
}
