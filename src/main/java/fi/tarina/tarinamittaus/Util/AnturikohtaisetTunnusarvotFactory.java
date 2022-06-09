package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvotDto;
import fi.tarina.tarinamittaus.Repository.AnturikohtaisetTunnusarvotRepository;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnturikohtaisetTunnusarvotFactory {

    @Autowired
    protected final AnturikohtaisetTunnusarvotRepository repository;

    public AnturikohtaisetTunnusarvotFactory(AnturikohtaisetTunnusarvotRepository repository) {this.repository = repository;}

    @ObjectFactory
    public AnturikohtaisetTunnusarvot create(AnturikohtaisetTunnusarvotDto dto){
        if (dto.getTunnusarvo_id() == null) return new AnturikohtaisetTunnusarvot();

        if (repository.exists(dto.getTunnusarvo_id()))
            return repository.getOne(dto.getTunnusarvo_id());

        return new AnturikohtaisetTunnusarvot();
    }
}
