package com.indra.selecao.selecaojava.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indra.selecao.selecaojava.DTO.PriceHistoryDTO;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryDTO, Long> {
	
	@Query(value = "SELECT (SUM(SALES_PRICE ) + SUM(PURCHASE_PRICE ))/(2*COUNT(1)) as AVERAGE FROM PRICE_HISTORY WHERE CITY = UPPER(:city)", nativeQuery = true)
	public Optional<Double> getGeneralPriceAverageByCity(@Param(value = "city") String cityName);
	
	@Query(value = "SELECT SUM(SALES_PRICE )/(COUNT(1)) as AVERAGE FROM PRICE_HISTORY WHERE CITY = UPPER(:city)", nativeQuery = true)
	public Optional<Double> getSalesPriceAverageByCity(@Param(value = "city") String cityName);
	
	@Query(value = "SELECT SUM(PURCHASE_PRICE )/COUNT(1) as AVERAGE FROM PRICE_HISTORY WHERE CITY = UPPER(:city)", nativeQuery = true)
	public Optional<Double> getPurchasePriceAverageByCity(@Param(value = "city") String cityName);
	
	@Query(value = "SELECT SUM(SALES_PRICE )/COUNT(1) as AVERAGE FROM PRICE_HISTORY WHERE LABEL = UPPER(:label)", nativeQuery = true)
	public Optional<Double> getSalesPriceAverageByLabel(@Param(value = "label") String labelName);
	
	@Query(value = "SELECT SUM(PURCHASE_PRICE )/COUNT(1) as AVERAGE FROM PRICE_HISTORY WHERE LABEL = UPPER(:label)", nativeQuery = true)
	public Optional<Double> getPurchasePriceAverageByLabel(@Param(value = "label") String labelName);
	
	@Query(value = "SELECT * FROM PRICE_HISTORY p WHERE REGION = UPPER(:region)", nativeQuery = true)
	public Page<PriceHistoryDTO> getAllByRegion(@Param(value = "region") String regionAcronym, Pageable pageable);
	
}
