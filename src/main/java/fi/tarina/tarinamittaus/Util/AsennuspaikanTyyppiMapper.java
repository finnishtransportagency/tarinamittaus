package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppiDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {AsennuspaikanTyyppiFactory.class})
public interface AsennuspaikanTyyppiMapper {
    AsennuspaikanTyyppiDto asennuspaikanTyyppiToDto(AsennuspaikanTyyppi tyyppi);
    AsennuspaikanTyyppi dtoToAsennuspaikanTyyppi(AsennuspaikanTyyppiDto dto);
}
