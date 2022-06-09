package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvotDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {AnturikohtaisetTunnusarvotFactory.class})
public interface AnturikohtaisetTunnusarvotMapper {

    AnturikohtaisetTunnusarvotDto anturikohtaisetTunnusarvotToDto(AnturikohtaisetTunnusarvot tunnusarvot);
    AnturikohtaisetTunnusarvot dtoToAnturikohtaisetTunnusArvot(AnturikohtaisetTunnusarvotDto dto);
}
