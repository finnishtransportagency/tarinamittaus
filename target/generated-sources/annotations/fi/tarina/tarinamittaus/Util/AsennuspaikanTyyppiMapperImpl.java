package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppiDto;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-08T16:19:54+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_192 (Oracle Corporation)"
)
@Component
public class AsennuspaikanTyyppiMapperImpl implements AsennuspaikanTyyppiMapper {

    @Autowired
    private AsennuspaikanTyyppiFactory asennuspaikanTyyppiFactory;

    @Override
    public AsennuspaikanTyyppiDto asennuspaikanTyyppiToDto(AsennuspaikanTyyppi tyyppi) {
        if ( tyyppi == null ) {
            return null;
        }

        AsennuspaikanTyyppiDto asennuspaikanTyyppiDto = new AsennuspaikanTyyppiDto();

        asennuspaikanTyyppiDto.setPaikkatyyppi_id( tyyppi.getPaikkatyyppi_id() );
        asennuspaikanTyyppiDto.setSelite( tyyppi.getSelite() );
        asennuspaikanTyyppiDto.setLisatiedot( tyyppi.getLisatiedot() );

        return asennuspaikanTyyppiDto;
    }

    @Override
    public AsennuspaikanTyyppi dtoToAsennuspaikanTyyppi(AsennuspaikanTyyppiDto dto) {
        if ( dto == null ) {
            return null;
        }

        AsennuspaikanTyyppi asennuspaikanTyyppi = asennuspaikanTyyppiFactory.create( dto );

        asennuspaikanTyyppi.setPaikkatyyppi_id( dto.getPaikkatyyppi_id() );
        asennuspaikanTyyppi.setSelite( dto.getSelite() );
        asennuspaikanTyyppi.setLisatiedot( dto.getLisatiedot() );

        return asennuspaikanTyyppi;
    }
}
