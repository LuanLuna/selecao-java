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

import com.indra.selecao.selecaojava.entity.State;
import com.indra.selecao.selecaojava.repository.StateRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "State")
@RequestMapping(value = "/state")
public class StateController {
	@Autowired
	private StateRepository stateRepository;
	
	Logger logger = LoggerFactory.getLogger(StateController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os estados")
	public List<State> getAll(){
		List<State> state = new ArrayList<State>();
		
		try {
			state = stateRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return state;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o estado referente ao id passado como parâmetro")
	public ResponseEntity<State> getById(@PathVariable(value = "id") Long stateId) {
		Optional<State> state;
		
		try {
			state = stateRepository.findById(stateId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (state.isPresent()) {
			return new ResponseEntity<State>(state.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um novo estado")
	public ResponseEntity<State> post(@Valid @RequestBody State State) {
		
		if (State != null) {			
			try {
				stateRepository.save(State);
				return new ResponseEntity<State>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar estado!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados do estado")
	public ResponseEntity<State> put(@PathVariable(value = "id") Long StateId, @Valid @RequestBody State newState) {
		Optional<State> storedState;
		
		try {
			storedState = stateRepository.findById(StateId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o estado id(" + StateId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedState.isPresent()) {
			newState.setId(storedState.get().getId());
			
			try {
				newState = stateRepository.save(newState);
				return new ResponseEntity<State>(newState, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar o estado id(" + StateId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um estado do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long StateId) {
		Optional<State> State;
		
		try {
			State = stateRepository.findById(StateId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o estado id(" + StateId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (State.isPresent()) {
			try {
				stateRepository.delete(State.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar o estado id(" + StateId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
