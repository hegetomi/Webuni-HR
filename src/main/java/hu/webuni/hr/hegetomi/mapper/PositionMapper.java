package hu.webuni.hr.hegetomi.mapper;

import hu.webuni.hr.hegetomi.dto.PositionDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.model.company.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    List<PositionDto> positionsToDtos(List<Position> positions);

    PositionDto positionToDto(Position position);

    Company dtoToPosition(Position dto);

}
