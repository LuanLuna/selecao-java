package com.indra.selecao.selecaojava.DTO;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.indra.selecao.selecaojava.entity.City;
import com.indra.selecao.selecaojava.entity.Label;
import com.indra.selecao.selecaojava.entity.Merchant;
import com.indra.selecao.selecaojava.entity.PriceHistory;
import com.indra.selecao.selecaojava.entity.Product;
import com.indra.selecao.selecaojava.entity.RegionEnum;
import com.indra.selecao.selecaojava.entity.State;

public abstract class PriceHistoryConverter {
	
	public static PriceHistoryDTO toDTO(PriceHistory entity) {
		PriceHistoryDTO newDTO = new PriceHistoryDTO();
		
		newDTO.setCity(entity.getCity().getName());
		newDTO.setDate(entity.getDate());
		newDTO.setId(entity.getId());
		newDTO.setLabel(entity.getLabel().getName());
		newDTO.setMerchant(entity.getMerchant().getName());
		newDTO.setMerchantCnpj(entity.getMerchant().getCnpj());
		newDTO.setMesure(entity.getProduct().getMeasure());
		newDTO.setProduct(entity.getProduct().getName());
		newDTO.setPurchasePrice(entity.getPurchasePrice());
		newDTO.setRegion(entity.getCity().getState().getRegion().getAcronym());
		newDTO.setSalesPrice(entity.getSalesPrice());
		newDTO.setState(entity.getCity().getState().getName());
		
		return newDTO;
	}
	
	public static PriceHistory toEntity(PriceHistoryDTO dto) {
		PriceHistory newEntity = new PriceHistory();
		
		City city = new City();
		State state = new State();
		Merchant merchant = new Merchant();
		Product product = new Product();
		Label label = new Label();
		
		state.setName(dto.getState());
		state.setRegion(RegionEnum.getByAcronym(dto.getRegion()));
		
		city.setName(dto.getCity());
		city.setState(state);
		
		merchant.setCnpj(dto.getMerchantCnpj());
		merchant.setName(dto.getMerchant());
		
		product.setName(dto.getProduct());
		product.setMeasure(dto.getMesure());
		
		label.setName(dto.getLabel());
		
		newEntity.setCity(city);
		newEntity.setMerchant(merchant);
		newEntity.setProduct(product);
		newEntity.setDate(dto.getDate());
		newEntity.setId(dto.getId());
		newEntity.setPurchasePrice(dto.getPurchasePrice());
		newEntity.setSalesPrice(dto.getSalesPrice());
		newEntity.setLabel(label);
		
		return newEntity;
	}
	
	public static PriceHistoryDTO csvToDTO(String line, String delimiter) throws ParseException {
		PriceHistoryDTO newDTO = new PriceHistoryDTO();
		SimpleDateFormat fileFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dataBaseFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String[] data = line.split(delimiter);
		Date date = Date.valueOf(dataBaseFormat.format(fileFormat.parse(data[6].trim())));
		Double salesPrice = data[7].isBlank() ? 0 : Double.valueOf(data[7].replace(",", ".").trim());
		Double puchasePrice = data[8].isBlank() ? 0 : Double.valueOf(data[8].replace(",", ".").trim());
		
		newDTO.setRegion(data[0].trim());
		newDTO.setState(data[1].trim());
		newDTO.setCity(data[2].trim());
		newDTO.setMerchant(data[3].trim());
		newDTO.setMerchantCnpj(data[4].trim());
		newDTO.setProduct(data[5].trim());
		newDTO.setDate(date);
		newDTO.setSalesPrice(salesPrice);
		newDTO.setPurchasePrice(puchasePrice);
		newDTO.setMesure(data[9].trim());
		newDTO.setLabel(data[10].trim());
		
		return newDTO;
	}

}
