package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AsennettuAnturiMapper.class})
public interface MittausMapper {

    @Mapping(target = "asennettuAnturiSet", source = "asennettuAnturiDtos")
    void updateMittausFromDto(MittausDto dto, @MappingTarget Mittaus mittaus);

    @Mapping(target = "asennettuAnturiDtos", source = "asennettuAnturiSet")
    void updateDtoFromMittaus(Mittaus mittaus, @MappingTarget MittausDto mittausDto);

}
