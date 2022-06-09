package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppiDto;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AsennuspaikanTyyppiFactory {
    @Autowired
    protected final AsennuspaikanTyyppiRepository repository;

    public AsennuspaikanTyyppiFactory(AsennuspaikanTyyppiRepository repository) {this.repository = repository;}

    @ObjectFactory
    public AsennuspaikanTyyppi create(AsennuspaikanTyyppiDto dto) {
        if (dto.getPaikkatyyppi_id() == null) return new AsennuspaikanTyyppi();

        if (repository.exists(dto.getPaikkatyyppi_id()))
            return repository.findOne(dto.getPaikkatyyppi_id());

        return new AsennuspaikanTyyppi();
    }
}
