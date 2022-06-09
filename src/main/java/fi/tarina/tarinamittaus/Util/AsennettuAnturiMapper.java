package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AsennettuAnturiFactory.class,
        AsennuspaikanTyyppiMapper.class,
        AnturikohtaisetTunnusarvotMapper.class})
public interface AsennettuAnturiMapper {

    @Mapping(target = "asennuspaikanTyyppiDto", source = "asennuspaikanTyyppi")
    @Mapping(target = "anturikohtaisetTunnusarvotDtos", source = "anturikohtaisetTunnusarvotSet")
    AsennettuAnturiDto asennettuAnturiToDto(AsennettuAnturi anturi);

    @InheritInverseConfiguration(name = "asennettuAnturiToDto")
    AsennettuAnturi dtoToAsennettuAnturi(AsennettuAnturiDto dto);
}
