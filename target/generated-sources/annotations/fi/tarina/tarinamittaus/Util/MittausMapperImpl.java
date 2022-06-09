package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
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
public class MittausMapperImpl implements MittausMapper {

    private final AsennettuAnturiMapper asennettuAnturiMapper;

    @Autowired
    public MittausMapperImpl(AsennettuAnturiMapper asennettuAnturiMapper) {

        this.asennettuAnturiMapper = asennettuAnturiMapper;
    }

    @Override
    public void updateMittausFromDto(MittausDto dto, Mittaus mittaus) {
        if ( dto == null ) {
            return;
        }

        if ( mittaus.getAsennettuAnturiSet() != null ) {
            List<AsennettuAnturi> list = asennettuAnturiDtoListToAsennettuAnturiList( dto.getAsennettuAnturiDtos() );
            if ( list != null ) {
                mittaus.getAsennettuAnturiSet().clear();
                mittaus.getAsennettuAnturiSet().addAll( list );
            }
            else {
                mittaus.setAsennettuAnturiSet( null );
            }
        }
        else {
            List<AsennettuAnturi> list = asennettuAnturiDtoListToAsennettuAnturiList( dto.getAsennettuAnturiDtos() );
            if ( list != null ) {
                mittaus.setAsennettuAnturiSet( list );
            }
        }
        mittaus.setKohde_id( dto.getKohde_id() );
        mittaus.setAlkuaika( dto.getAlkuaika() );
        mittaus.setLoppuaika( dto.getLoppuaika() );
        mittaus.setMittaus_asianhallinta_id( dto.getMittaus_asianhallinta_id() );
        mittaus.setPdf_raportin_linkki( dto.getPdf_raportin_linkki() );
        mittaus.setRakennuksen_pinta_ala( dto.getRakennuksen_pinta_ala() );
        mittaus.setPerustamistapa( dto.getPerustamistapa() );
        mittaus.setJulkisivumateriaali( dto.getJulkisivumateriaali() );
        mittaus.setRunkomateriaali( dto.getRunkomateriaali() );
        mittaus.setRakennusvuosi( dto.getRakennusvuosi() );
        mittaus.setKatuosoite( dto.getKatuosoite() );
        mittaus.setPostinumero( dto.getPostinumero() );
        mittaus.setCreated_by_lx( dto.getCreated_by_lx() );
    }

    @Override
    public void updateDtoFromMittaus(Mittaus mittaus, MittausDto mittausDto) {
        if ( mittaus == null ) {
            return;
        }

        if ( mittausDto.getAsennettuAnturiDtos() != null ) {
            List<AsennettuAnturiDto> list = asennettuAnturiListToAsennettuAnturiDtoList( mittaus.getAsennettuAnturiSet() );
            if ( list != null ) {
                mittausDto.getAsennettuAnturiDtos().clear();
                mittausDto.getAsennettuAnturiDtos().addAll( list );
            }
            else {
                mittausDto.setAsennettuAnturiDtos( null );
            }
        }
        else {
            List<AsennettuAnturiDto> list = asennettuAnturiListToAsennettuAnturiDtoList( mittaus.getAsennettuAnturiSet() );
            if ( list != null ) {
                mittausDto.setAsennettuAnturiDtos( list );
            }
        }
        mittausDto.setKohde_id( mittaus.getKohde_id() );
        mittausDto.setAlkuaika( mittaus.getAlkuaika() );
        mittausDto.setLoppuaika( mittaus.getLoppuaika() );
        mittausDto.setMittaus_asianhallinta_id( mittaus.getMittaus_asianhallinta_id() );
        mittausDto.setPdf_raportin_linkki( mittaus.getPdf_raportin_linkki() );
        mittausDto.setRakennuksen_pinta_ala( mittaus.getRakennuksen_pinta_ala() );
        mittausDto.setPerustamistapa( mittaus.getPerustamistapa() );
        mittausDto.setJulkisivumateriaali( mittaus.getJulkisivumateriaali() );
        mittausDto.setRunkomateriaali( mittaus.getRunkomateriaali() );
        mittausDto.setRakennusvuosi( mittaus.getRakennusvuosi() );
        mittausDto.setKatuosoite( mittaus.getKatuosoite() );
        mittausDto.setPostinumero( mittaus.getPostinumero() );
        mittausDto.setCreated_by_lx( mittaus.getCreated_by_lx() );
    }

    protected List<AsennettuAnturi> asennettuAnturiDtoListToAsennettuAnturiList(List<AsennettuAnturiDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AsennettuAnturi> list1 = new ArrayList<AsennettuAnturi>( list.size() );
        for ( AsennettuAnturiDto asennettuAnturiDto : list ) {
            list1.add( asennettuAnturiMapper.dtoToAsennettuAnturi( asennettuAnturiDto ) );
        }

        return list1;
    }

    protected List<AsennettuAnturiDto> asennettuAnturiListToAsennettuAnturiDtoList(List<AsennettuAnturi> list) {
        if ( list == null ) {
            return null;
        }

        List<AsennettuAnturiDto> list1 = new ArrayList<AsennettuAnturiDto>( list.size() );
        for ( AsennettuAnturi asennettuAnturi : list ) {
            list1.add( asennettuAnturiMapper.asennettuAnturiToDto( asennettuAnturi ) );
        }

        return list1;
    }
}
