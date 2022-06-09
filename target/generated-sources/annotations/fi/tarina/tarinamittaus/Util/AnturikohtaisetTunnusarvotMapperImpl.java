package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvotDto;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-08T16:19:54+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class AnturikohtaisetTunnusarvotMapperImpl implements AnturikohtaisetTunnusarvotMapper {

    @Autowired
    private AnturikohtaisetTunnusarvotFactory anturikohtaisetTunnusarvotFactory;

    @Override
    public AnturikohtaisetTunnusarvotDto anturikohtaisetTunnusarvotToDto(AnturikohtaisetTunnusarvot tunnusarvot) {
        if ( tunnusarvot == null ) {
            return null;
        }

        AnturikohtaisetTunnusarvotDto anturikohtaisetTunnusarvotDto = new AnturikohtaisetTunnusarvotDto();

        anturikohtaisetTunnusarvotDto.setTunnusarvo_id( tunnusarvot.getTunnusarvo_id() );
        anturikohtaisetTunnusarvotDto.setMittaussuunta_xyz( tunnusarvot.getMittaussuunta_xyz() );
        anturikohtaisetTunnusarvotDto.setTarinan_maksimiarvo( tunnusarvot.getTarinan_maksimiarvo() );
        anturikohtaisetTunnusarvotDto.setHallitseva_taajuus( tunnusarvot.getHallitseva_taajuus() );
        anturikohtaisetTunnusarvotDto.setTarinan_tunnusluku_vw95_rms( tunnusarvot.getTarinan_tunnusluku_vw95_rms() );

        return anturikohtaisetTunnusarvotDto;
    }

    @Override
    public AnturikohtaisetTunnusarvot dtoToAnturikohtaisetTunnusArvot(AnturikohtaisetTunnusarvotDto dto) {
        if ( dto == null ) {
            return null;
        }

        AnturikohtaisetTunnusarvot anturikohtaisetTunnusarvot = anturikohtaisetTunnusarvotFactory.create( dto );

        anturikohtaisetTunnusarvot.setTunnusarvo_id( dto.getTunnusarvo_id() );
        anturikohtaisetTunnusarvot.setMittaussuunta_xyz( dto.getMittaussuunta_xyz() );
        anturikohtaisetTunnusarvot.setTarinan_maksimiarvo( dto.getTarinan_maksimiarvo() );
        anturikohtaisetTunnusarvot.setHallitseva_taajuus( dto.getHallitseva_taajuus() );
        anturikohtaisetTunnusarvot.setTarinan_tunnusluku_vw95_rms( dto.getTarinan_tunnusluku_vw95_rms() );

        return anturikohtaisetTunnusarvot;
    }
}
