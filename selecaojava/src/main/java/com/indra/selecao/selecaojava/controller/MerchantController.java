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

import com.indra.selecao.selecaojava.entity.Merchant;
import com.indra.selecao.selecaojava.repository.MerchantRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Merchant")
@RequestMapping(value = "/merchant")
public class MerchantController {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	Logger logger = LoggerFactory.getLogger(MerchantController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os pontos de venda")
	public List<Merchant> getAll(){
		List<Merchant> merchants = new ArrayList<Merchant>();
		
		try {
			merchants = merchantRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return merchants;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o ponto de venda referente ao id passado como parâmetro")
	public ResponseEntity<Merchant> getById(@PathVariable(value = "id") Long merchantId) {
		Optional<Merchant> merchant;
		
		try {
			merchant = merchantRepository.findById(merchantId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (merchant.isPresent()) {
			return new ResponseEntity<Merchant>(merchant.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um novo ponto de venda")
	public ResponseEntity<Merchant> post(@Valid @RequestBody Merchant merchant) {
		
		if (merchant != null) {			
			try {
				merchantRepository.save(merchant);
				return new ResponseEntity<Merchant>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar ponto de venda!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados do ponto de venda")
	public ResponseEntity<Merchant> put(@PathVariable(value = "id") Long merchantId, @Valid @RequestBody Merchant newMerchant) {
		Optional<Merchant> storedMerchant;
		
		try {
			storedMerchant = merchantRepository.findById(merchantId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o ponto de venda id(" + merchantId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedMerchant.isPresent()) {
			newMerchant.setId(storedMerchant.get().getId());
			
			try {
				newMerchant = merchantRepository.save(newMerchant);
				return new ResponseEntity<Merchant>(newMerchant, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar o ponto de venda id(" + merchantId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um ponto de venda do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long merchantId) {
		Optional<Merchant> merchant;
		
		try {
			merchant = merchantRepository.findById(merchantId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o ponto de venda id(" + merchantId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (merchant.isPresent()) {
			try {
				merchantRepository.delete(merchant.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar o ponto de venda id(" + merchantId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


}
