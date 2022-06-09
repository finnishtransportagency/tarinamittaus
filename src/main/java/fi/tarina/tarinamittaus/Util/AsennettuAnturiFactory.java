package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import fi.tarina.tarinamittaus.Model.AsennettuAnturiDto;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AsennettuAnturiFactory {
    @Autowired
    protected final AnturiRepository anturiRepository;

    public AsennettuAnturiFactory(AnturiRepository anturiRepository) {this.anturiRepository = anturiRepository;}

    @ObjectFactory
    public AsennettuAnturi create(AsennettuAnturiDto dto) {
        if(dto.getAsennuskohtainen_id()==null) return new AsennettuAnturi();

        if (anturiRepository.exists(dto.getAsennuskohtainen_id()))
            return anturiRepository.findOne(dto.getAsennuskohtainen_id());

        return new AsennettuAnturi();

    }
}
