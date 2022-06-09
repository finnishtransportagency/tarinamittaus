package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvotDto;
import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-08T16:19:54+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class AsennettuAnturiMapperImpl implements AsennettuAnturiMapper {

    @Autowired
    private AsennettuAnturiFactory asennettuAnturiFactory;
    @Autowired
    private AsennuspaikanTyyppiMapper asennuspaikanTyyppiMapper;
    @Autowired
    private AnturikohtaisetTunnusarvotMapper anturikohtaisetTunnusarvotMapper;

    @Override
    public AsennettuAnturiDto asennettuAnturiToDto(AsennettuAnturi anturi) {
        if ( anturi == null ) {
            return null;
        }

        AsennettuAnturiDto asennettuAnturiDto = new AsennettuAnturiDto();

        asennettuAnturiDto.setAsennuspaikanTyyppiDto( asennuspaikanTyyppiMapper.asennuspaikanTyyppiToDto( anturi.getAsennuspaikanTyyppi() ) );
        asennettuAnturiDto.setAnturikohtaisetTunnusarvotDtos( anturikohtaisetTunnusarvotListToAnturikohtaisetTunnusarvotDtoList( anturi.getAnturikohtaisetTunnusarvotSet() ) );
        asennettuAnturiDto.setAsennuskohtainen_id( anturi.getAsennuskohtainen_id() );
        asennettuAnturiDto.setMalli( anturi.getMalli() );
        asennettuAnturiDto.setGpsLat( anturi.getGpsLat() );
        asennettuAnturiDto.setGpsLong( anturi.getGpsLong() );
        asennettuAnturiDto.setEtaisyysRadastaJosEri( anturi.getEtaisyysRadastaJosEri() );
        asennettuAnturiDto.setKerros( anturi.getKerros() );
        asennettuAnturiDto.setSijoituspaikanLisaselite( anturi.getSijoituspaikanLisaselite() );

        return asennettuAnturiDto;
    }

    @Override
    public AsennettuAnturi dtoToAsennettuAnturi(AsennettuAnturiDto dto) {
        if ( dto == null ) {
            return null;
        }

        AsennettuAnturi asennettuAnturi = asennettuAnturiFactory.create( dto );

        asennettuAnturi.setAsennuspaikanTyyppi( asennuspaikanTyyppiMapper.dtoToAsennuspaikanTyyppi( dto.getAsennuspaikanTyyppiDto() ) );
        asennettuAnturi.setAnturikohtaisetTunnusarvotSet( anturikohtaisetTunnusarvotDtoListToAnturikohtaisetTunnusarvotList( dto.getAnturikohtaisetTunnusarvotDtos() ) );
        asennettuAnturi.setAsennuskohtainen_id( dto.getAsennuskohtainen_id() );
        asennettuAnturi.setMalli( dto.getMalli() );
        asennettuAnturi.setGpsLat( dto.getGpsLat() );
        asennettuAnturi.setGpsLong( dto.getGpsLong() );
        asennettuAnturi.setEtaisyysRadastaJosEri( dto.getEtaisyysRadastaJosEri() );
        asennettuAnturi.setKerros( dto.getKerros() );
        asennettuAnturi.setSijoituspaikanLisaselite( dto.getSijoituspaikanLisaselite() );

        return asennettuAnturi;
    }

    protected List<AnturikohtaisetTunnusarvotDto> anturikohtaisetTunnusarvotListToAnturikohtaisetTunnusarvotDtoList(List<AnturikohtaisetTunnusarvot> list) {
        if ( list == null ) {
            return null;
        }

        List<AnturikohtaisetTunnusarvotDto> list1 = new ArrayList<AnturikohtaisetTunnusarvotDto>( list.size() );
        for ( AnturikohtaisetTunnusarvot anturikohtaisetTunnusarvot : list ) {
            list1.add( anturikohtaisetTunnusarvotMapper.anturikohtaisetTunnusarvotToDto( anturikohtaisetTunnusarvot ) );
        }

        return list1;
    }

    protected List<AnturikohtaisetTunnusarvot> anturikohtaisetTunnusarvotDtoListToAnturikohtaisetTunnusarvotList(List<AnturikohtaisetTunnusarvotDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AnturikohtaisetTunnusarvot> list1 = new ArrayList<AnturikohtaisetTunnusarvot>( list.size() );
        for ( AnturikohtaisetTunnusarvotDto anturikohtaisetTunnusarvotDto : list ) {
            list1.add( anturikohtaisetTunnusarvotMapper.dtoToAnturikohtaisetTunnusArvot( anturikohtaisetTunnusarvotDto ) );
        }

        return list1;
    }
}
