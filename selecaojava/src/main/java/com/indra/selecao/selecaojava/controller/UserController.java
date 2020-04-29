package com.indra.selecao.selecaojava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.selecao.selecaojava.entity.User;
import com.indra.selecao.selecaojava.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User")
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os usuários")
	public List<User> getAll(
			@RequestParam(required = true, defaultValue = "0") int page, 
			@RequestParam(required = true, defaultValue = "50") int size){
		
		Page<User> pageResult;
		List<User> users = new ArrayList<User>();
		
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
			pageResult = userRepository.findAll(pageable);
			
			if(pageResult.hasContent()) {
				users = pageResult.getContent();
			}
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return users;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o usuário referente ao id passado como parâmetro")
	public ResponseEntity<User> getById(@PathVariable(value = "id") Long userId) {
		Optional<User> user;
		
		try {
			user = userRepository.findById(userId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um novo usuário")
	public ResponseEntity<User> post(@Valid @RequestBody User user) {
		
		if (user != null) {			
			try {
				userRepository.save(user);
				return new ResponseEntity<User>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar usuário!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados do usuário")
	public ResponseEntity<User> put(@PathVariable(value = "id") Long userId, @Valid @RequestBody User newUser) {
		Optional<User> storedUser;
		
		try {
			storedUser = userRepository.findById(userId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o usuário id(" + userId + ")!");
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedUser.isPresent()) {
			newUser.setId(storedUser.get().getId());
			
			try {
				newUser = userRepository.save(newUser);
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar o usuário id(" + userId + ")!");
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um usuário do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long userId) {
		Optional<User> user;
		
		try {
			user = userRepository.findById(userId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o usuário id(" + userId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (user.isPresent()) {
			try {
				userRepository.delete(user.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar o usuário id(" + userId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
