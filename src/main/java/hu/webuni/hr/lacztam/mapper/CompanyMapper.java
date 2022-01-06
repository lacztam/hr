package hu.webuni.hr.lacztam.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDtos(List<Company> companies);
	
	CompanyDto companytoDto(Company company);
	
	Company dtoToCompany (CompanyDto companyDto);
	
	

}
