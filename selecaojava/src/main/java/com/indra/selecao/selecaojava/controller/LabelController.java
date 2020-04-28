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

import com.indra.selecao.selecaojava.entity.Label;
import com.indra.selecao.selecaojava.repository.LabelRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Label")
@RequestMapping(value = "/label")
public class LabelController {
	
	@Autowired
	private LabelRepository labelRepository;
	
	Logger logger = LoggerFactory.getLogger(LabelController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todas as Labels")
	public List<Label> getAll(){
		List<Label> labels = new ArrayList<Label>();
		
		try {
			labels = labelRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return labels;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna a label referente ao id passado como parâmetro")
	public ResponseEntity<Label> getById(@PathVariable(value = "id") Long labelId) {
		Optional<Label> label;
		
		try {
			label = labelRepository.findById(labelId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (label.isPresent()) {
			return new ResponseEntity<Label>(label.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere uma nova label")
	public ResponseEntity<Label> post(@Valid @RequestBody Label label) {
		
		if (label != null) {			
			try {
				labelRepository.save(label);
				return new ResponseEntity<Label>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar label!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados da label")
	public ResponseEntity<Label> put(@PathVariable(value = "id") Long labelId, @Valid @RequestBody Label newLabel) {
		Optional<Label> storedLabel;
		
		try {
			storedLabel = labelRepository.findById(labelId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a label id(" + labelId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedLabel.isPresent()) {
			newLabel.setId(storedLabel.get().getId());
			
			try {
				newLabel = labelRepository.save(newLabel);
				return new ResponseEntity<Label>(newLabel, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar a label id(" + labelId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove uma label do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long labelId) {
		Optional<Label> label;
		
		try {
			label = labelRepository.findById(labelId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a label id(" + labelId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (label.isPresent()) {
			try {
				labelRepository.delete(label.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar a label id(" + labelId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
