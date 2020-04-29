package com.indra.selecao.selecaojava.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
import org.springframework.web.multipart.MultipartFile;

import com.indra.selecao.selecaojava.DTO.PriceHistoryConverter;
import com.indra.selecao.selecaojava.DTO.PriceHistoryDTO;
import com.indra.selecao.selecaojava.entity.PriceHistory;
import com.indra.selecao.selecaojava.entity.RegionEnum;
import com.indra.selecao.selecaojava.repository.PriceHistoryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "PriceHistory")
@RequestMapping(value = "/pricehistory")
public class PriceHistoryController {
	@Autowired
	private PriceHistoryRepository priceHistoryRepository;
	
	Logger logger = LoggerFactory.getLogger(PriceHistoryController.class);
	


	@RequestMapping(value = "",method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os históricos de preço")
	public List<PriceHistory> getAll(
			@RequestParam(required = true, defaultValue = "0") int page, 
			@RequestParam(required = true, defaultValue = "50") int size){
		
		List<PriceHistoryDTO> priceHistoryDtoList = new ArrayList<PriceHistoryDTO>();
		List<PriceHistory> priceHistoryList = new ArrayList<PriceHistory>();
		Page<PriceHistoryDTO> pageResult;
		
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
			pageResult = priceHistoryRepository.findAll(pageable);
			
			if(pageResult.hasContent()) {
				priceHistoryDtoList = pageResult.getContent();
			}
			
			for (PriceHistoryDTO current : priceHistoryDtoList) {
				priceHistoryList.add(PriceHistoryConverter.toEntity(current));
			}
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return priceHistoryList;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o histórico de preço referente ao id passado como parâmetro")
	public ResponseEntity<PriceHistory> getById(@PathVariable(value = "id") Long priceHistoryId) {
		Optional<PriceHistoryDTO> priceHistoryDTO;
		
		try {
			priceHistoryDTO = priceHistoryRepository.findById(priceHistoryId);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (priceHistoryDTO.isPresent()) {
			return new ResponseEntity<PriceHistory>(PriceHistoryConverter.toEntity(priceHistoryDTO.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Insere um novo histórico de preço")
	public ResponseEntity<PriceHistory> post(@Valid @RequestBody PriceHistory priceHistory) {
		
		if (priceHistory != null) {			
			try {
				priceHistoryRepository.save(PriceHistoryConverter.toDTO(priceHistory));
				return new ResponseEntity<PriceHistory>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar salvar histórico de preço!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Altera os dados do histórico de preço")
	public ResponseEntity<PriceHistory> put(@PathVariable(value = "id") Long PriceHistoryId, @Valid @RequestBody PriceHistory newPriceHistory) {
		Optional<PriceHistoryDTO> storedPriceHistory;
		
		try {
			storedPriceHistory = priceHistoryRepository.findById(PriceHistoryId);
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o histórico de preço id(" + PriceHistoryId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (storedPriceHistory.isPresent()) {
			newPriceHistory.setId(storedPriceHistory.get().getId());
			
			try {
				PriceHistoryDTO updatedPriceHistory = priceHistoryRepository.save(PriceHistoryConverter.toDTO(newPriceHistory));
				newPriceHistory = PriceHistoryConverter.toEntity(updatedPriceHistory);
				return new ResponseEntity<PriceHistory>(newPriceHistory, HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar alterar o histórico de preço id(" + PriceHistoryId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remove um histórico de preço do sistema")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long PriceHistoryId) {
		Optional<PriceHistoryDTO> PriceHistory;
		
		try {
			PriceHistory = priceHistoryRepository.findById(PriceHistoryId);
			
		} catch (Exception e) {
			logger.error("Erro ao tentar buscar o histórico de preço id(" + PriceHistoryId + ")!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (PriceHistory.isPresent()) {
			try {
				priceHistoryRepository.delete(PriceHistory.get());
				return new ResponseEntity<>(HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Erro ao tentar deletar o histórico de preço id(" + PriceHistoryId + ")!");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/upload/file/csv", method = RequestMethod.POST)
	@ApiOperation(value = "Carrega um arquivo e salva os dados na base dedados")
	public ResponseEntity<Object> post(
			@RequestParam(required = true) MultipartFile file,
			@RequestParam(required = true) boolean hasHeader,
			@RequestParam(defaultValue = "\t") String delimiter,
			@RequestParam(defaultValue = "UTF-16") String encode) {
		
	    String line;
	    int currentLine = 1;
		
		InputStream inputStream;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e1) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Scanner scanner = new Scanner(inputStream, encode);
	    if (hasHeader && scanner.hasNext()) {
	    	line = scanner.nextLine();
	    }
	    
	    try {
	    	while (scanner.hasNext()) {
	    		line = scanner.nextLine();
	    		
	    		try {
	    			if (currentLine > 0 && currentLine % 100 == 0) {
	    				priceHistoryRepository.flush();
	    	        }
	    			PriceHistoryDTO newPriceHistoryDTO = PriceHistoryConverter.csvToDTO(line, delimiter);
	    			
	    			try {
	    				priceHistoryRepository.save(newPriceHistoryDTO);
	    			} catch (Exception e) {
	    				logger.error("Erro ao tentar salvar linha (" + currentLine + ") do arquivo!");
					}
	    		} catch (Exception e) {
	    			
	    			logger.error("Erro ao tentar interpretar linha (" + currentLine + ") do arquivo!");
				}
	    		
	    		currentLine++;
	    	}
	    	return new ResponseEntity<>(HttpStatus.OK);
	    	
	    } catch (Exception e) {
	    	
	    	logger.error("Erro ao tentar ler linha (" + currentLine + ") do arquivo!");
		} finally {
			scanner.close();
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/average_by_city/general/{city_name}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o valor medio do preço do combustível por cidade levando em consideralção compra e venda")
	public ResponseEntity<Object> getGeneralPriceAverageByCity(@PathVariable(value = "city_name", required = true) String city) {
		HashMap<String, Double> map = new HashMap<>();
		Optional<Double> average;
		
		try {
			average = priceHistoryRepository.getGeneralPriceAverageByCity(city);
			
			if(average.isPresent()) {
				map.put(ReturnTags.GENERAL_PRICE_AVERAGE.getTag(), average.get());
				
				return new ResponseEntity<>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			logger.error("Erro ao consultar base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/average_by_city/separeted/{city_name}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o valor medio do preço do combustível por cidade separando por compra e venda")
	public ResponseEntity<Object> getSeparetedPriceAverageByCity(@PathVariable(value = "city_name", required = true) String city) {
		HashMap<String, Double> map = new HashMap<>();
		Optional<Double> average;
		
		try {
			average = priceHistoryRepository.getSalesPriceAverageByCity(city);
			
			if(average.isPresent()) {
				map.put(ReturnTags.PURCHASE_PRICE_AVERAGE.getTag(), average.get());
			}
			
			average = priceHistoryRepository.getPurchasePriceAverageByCity(city);
			
			if(average.isPresent()) {
				map.put(ReturnTags.SALES_PRICE_AVERAGE.getTag(), average.get());
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
			
			
		}catch (Exception e) {
			logger.error("Erro ao consultar base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/average_by_label/separeted/{label_name}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o valor medio do preço do combustível por bandeira separando por compra e venda")
	public ResponseEntity<Object> getSeparetedPriceAverageByLabel(@PathVariable(value = "label_name", required = true) String label) {
		HashMap<String, Double> map = new HashMap<>();
		Optional<Double> average;
		
		try {
			average = priceHistoryRepository.getSalesPriceAverageByLabel(label);
			
			if(average.isPresent()) {
				map.put(ReturnTags.PURCHASE_PRICE_AVERAGE.getTag(), average.get());
			}
			
			average = priceHistoryRepository.getPurchasePriceAverageByLabel(label);
			
			if(average.isPresent()) {
				map.put(ReturnTags.SALES_PRICE_AVERAGE.getTag(), average.get());
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
			
		}catch (Exception e) {
			logger.error("Erro ao consultar base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/region/{region}", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os registros por região")
	public List<PriceHistory> getAllByRegion(
			@PathVariable(value = "region", required = true) RegionEnum region,
			@RequestParam(required = true, defaultValue = "0") int page, 
			@RequestParam(required = true, defaultValue = "50") int size) {
		
		List<PriceHistoryDTO> priceHistoryDtoList = new ArrayList<PriceHistoryDTO>();
		List<PriceHistory> priceHistoryList = new ArrayList<PriceHistory>();
		Page<PriceHistoryDTO> pageResult;
		
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
			pageResult = priceHistoryRepository.getAllByRegion(region.getAcronym(), pageable);
			
			if (pageResult.hasContent()) {
				priceHistoryDtoList = pageResult.getContent();
				
				for (PriceHistoryDTO current : priceHistoryDtoList) {
					priceHistoryList.add(PriceHistoryConverter.toEntity(current));
				}
			}
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
		}

		return priceHistoryList;
	}
	
	@RequestMapping(value = "/grouped/merchant", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os registros agrupador por distribuidora")
	public ResponseEntity<Object> getAllGroupingByMerchant(
			@RequestParam(required = true, defaultValue = "0") int page, 
			@RequestParam(required = true, defaultValue = "50") int size) {
		
		List<PriceHistoryDTO> priceHistoryDtoList = new ArrayList<PriceHistoryDTO>();
		Page<PriceHistoryDTO> pageResult;
		HashMap<String, List<PriceHistory>> result = new HashMap<String, List<PriceHistory>>();
		
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("merchant").ascending());
			pageResult = priceHistoryRepository.findAll(pageable);
			
			if (pageResult.hasContent()) {
				priceHistoryDtoList = pageResult.getContent();
				
				for (PriceHistoryDTO current : priceHistoryDtoList) {
					PriceHistory currentEntity = PriceHistoryConverter.toEntity(current);
					String key = current.getMerchant();
					
					if (!result.containsKey(key)) {
						result.put(key, new ArrayList<PriceHistory>());
					}
					
					result.get(key).add(currentEntity);
				}
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/grouped/date", method = RequestMethod.GET)
	@ApiOperation(value = "Retorna todos os registros agrupador por data")
	public ResponseEntity<Object> getAllGroupingByDate(
			@RequestParam(required = true, defaultValue = "0") int page, 
			@RequestParam(required = true, defaultValue = "50") int size) {
		
		List<PriceHistoryDTO> priceHistoryDtoList = new ArrayList<PriceHistoryDTO>();
		Page<PriceHistoryDTO> pageResult;
		HashMap<String, List<PriceHistory>> result = new HashMap<String, List<PriceHistory>>();
		
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("date").ascending());
			pageResult = priceHistoryRepository.findAll(pageable);
			
			if (pageResult.hasContent()) {
				priceHistoryDtoList = pageResult.getContent();
				
				for (PriceHistoryDTO current : priceHistoryDtoList) {
					PriceHistory currentEntity = PriceHistoryConverter.toEntity(current);
					String key = current.getDate().toString();
					
					if (!result.containsKey(key)) {
						result.put(key, new ArrayList<PriceHistory>());
					}
					
					result.get(key).add(currentEntity);
				}

				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Erro ao se conectar com a base de dados!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private enum ReturnTags {
		GENERAL_PRICE_AVERAGE("GENERAL_PRICE_AVERAGE"),
		SALES_PRICE_AVERAGE("SALES_PRICE_AVERAGE"),
		PURCHASE_PRICE_AVERAGE("PURCHASE_PRICE_AVERAGE");
		
		private String tag;
		ReturnTags(String tag) {
			this.tag = tag;
		}
		
		public String getTag() {
			return this.tag;
		}
		
	}
}
