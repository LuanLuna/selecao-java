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

import com.indra.selecao.selecaojava.entity.Region;
import com.indra.selecao.selecaojava.repository.RegionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Region")
@RequestMapping(value = "/region")
public class RegionController {
	@Autowired
	private RegionRepository regionRepository;
	
	Logger logger = LoggerFactory.getLogger(RegionController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todas as regiões")
	public List<Region> getAll(){
		List<Region> region = new ArrayList<Region>();
		
		try {
			region = regionRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return region;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna a região referente ao id passado como parâmetro")
	public ResponseEntity<Region> getById(@PathVariable(value = "id") Long regionId) {
		Optional<Region> region;
		
		try {
			region = regionRepository.findById(regionId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (region.isPresent()) {
			return new ResponseEntity<Region>(region.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um nova região")
	public ResponseEntity<Region> post(@Valid @RequestBody Region region) {
		
		if (region != null) {			
			try {
				regionRepository.save(region);
				return new ResponseEntity<Region>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar região!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados da região")
	public ResponseEntity<Region> put(@PathVariable(value = "id") Long RegionId, @Valid @RequestBody Region newRegion) {
		Optional<Region> storedRegion;
		
		try {
			storedRegion = regionRepository.findById(RegionId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a região id(" + RegionId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedRegion.isPresent()) {
			newRegion.setId(storedRegion.get().getId());
			
			try {
				newRegion = regionRepository.save(newRegion);
				return new ResponseEntity<Region>(newRegion, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar a região id(" + RegionId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um região do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long RegionId) {
		Optional<Region> Region;
		
		try {
			Region = regionRepository.findById(RegionId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a região id(" + RegionId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (Region.isPresent()) {
			try {
				regionRepository.delete(Region.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar a região id(" + RegionId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
