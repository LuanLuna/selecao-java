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

import com.indra.selecao.selecaojava.entity.City;
import com.indra.selecao.selecaojava.repository.CityRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "City")
@RequestMapping(value = "/city")
public class CityController {
	@Autowired
	private CityRepository cityRepository;
	
	Logger logger = LoggerFactory.getLogger(CityController.class);
	


	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todas as cidades")
	public List<City> getAll(){
		List<City> city = new ArrayList<City>();
		
		try {
			city = cityRepository.findAll();
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return city;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna a cidade referente ao id passado como parâmetro")
	public ResponseEntity<City> getById(@PathVariable(value = "id") Long cityId) {
		Optional<City> city;
		
		try {
			city = cityRepository.findById(cityId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (city.isPresent()) {
			return new ResponseEntity<City>(city.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um nova cidade")
	public ResponseEntity<City> post(@Valid @RequestBody City city) {
		
		if (city != null) {			
			try {
				cityRepository.save(city);
				return new ResponseEntity<City>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar cidade!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados da cidade")
	public ResponseEntity<City> put(@PathVariable(value = "id") Long CityId, @Valid @RequestBody City newCity) {
		Optional<City> storedCity;
		
		try {
			storedCity = cityRepository.findById(CityId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a cidade id(" + CityId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedCity.isPresent()) {
			newCity.setId(storedCity.get().getId());
			
			try {
				newCity = cityRepository.save(newCity);
				return new ResponseEntity<City>(newCity, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar a cidade id(" + CityId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um cidade do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long CityId) {
		Optional<City> City;
		
		try {
			City = cityRepository.findById(CityId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar a cidade id(" + CityId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (City.isPresent()) {
			try {
				cityRepository.delete(City.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar a cidade id(" + CityId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
