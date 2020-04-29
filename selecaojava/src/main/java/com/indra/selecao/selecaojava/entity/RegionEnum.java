package com.indra.selecao.selecaojava.entity;

public enum RegionEnum {
	CENTRO_OESTE("CO"),
	SUL("S"),
	NODERTE("NE"),
	NORTE("N"),
	SUDESTE("SE"),
	UNDEFINED("UND");
	
	private String acronym;
	
	private RegionEnum(String acronym) {
		this.acronym  = acronym;
	}
	
	public static RegionEnum getByAcronym(String acronym) {
		RegionEnum[] values = RegionEnum.values();
		for (RegionEnum value : values) {
			if (value.acronym.equalsIgnoreCase(acronym)) {
				return value;
			}
		}
		
		return RegionEnum.UNDEFINED;
	}
	
	public String getAcronym() {
		return this.acronym;
	}

}
