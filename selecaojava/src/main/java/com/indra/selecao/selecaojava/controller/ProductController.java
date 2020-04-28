package com.indra.selecao.selecaojava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indra.selecao.selecaojava.entity.Product;
import com.indra.selecao.selecaojava.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Product")
@RequestMapping(value = "/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os produtos")
	public List<Product> getAll(){
		List<Product> product = new ArrayList<Product>();
		
		try {
			product = productRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return product;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o produto referente ao id passado como parâmetro")
	public ResponseEntity<Product> getById(@PathVariable(value = "id") Long productId) {
		Optional<Product> product;
		
		try {
			product = productRepository.findById(productId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (product.isPresent()) {
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um novo produto")
	public ResponseEntity<Product> post(@Valid @RequestBody Product Product) {
		
		if (Product != null) {			
			try {
				productRepository.save(Product);
				return new ResponseEntity<Product>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar produto!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados do produto")
	public ResponseEntity<Product> put(@PathVariable(value = "id") Long ProductId, @Valid @RequestBody Product newProduct) {
		Optional<Product> storedProduct;
		
		try {
			storedProduct = productRepository.findById(ProductId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o produto id(" + ProductId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedProduct.isPresent()) {
			newProduct.setId(storedProduct.get().getId());
			
			try {
				newProduct = productRepository.save(newProduct);
				return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar o produto id(" + ProductId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um produto do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long ProductId) {
		Optional<Product> Product;
		
		try {
			Product = productRepository.findById(ProductId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o produto id(" + ProductId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (Product.isPresent()) {
			try {
				productRepository.delete(Product.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar o produto id(" + ProductId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
