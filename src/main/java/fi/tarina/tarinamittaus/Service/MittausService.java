package fi.tarina.tarinamittaus.Service;

import fi.tarina.tarinamittaus.Model.*;
import fi.tarina.tarinamittaus.Repository.AnturiRepository;
import fi.tarina.tarinamittaus.Repository.AnturikohtaisetTunnusarvotRepository;
import fi.tarina.tarinamittaus.Repository.MittausRepository;
import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
import fi.tarina.tarinamittaus.Specification.MittausSearchParameters;
import fi.tarina.tarinamittaus.Specification.MittausSpecifications;
import fi.tarina.tarinamittaus.Util.MittausMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MittausService {

    private final Logger LOG = LoggerFactory.getLogger(MittausService.class);

    private final MittausRepository mittausRepository;
    private final AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository;
    private final AnturiRepository anturiRepository;
    private final AnturikohtaisetTunnusarvotRepository tunnusarvotRepository;
    private final MittausMapper mittausMapper;

    @Autowired
    public MittausService(MittausRepository mittausRepository,
                          AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository,
                          AnturiRepository anturiRepository,
                          AnturikohtaisetTunnusarvotRepository tunnusarvotRepository,
                          MittausMapper mittausMapper
                         ) {
        this.mittausRepository = mittausRepository;
        this.asennuspaikanTyyppiRepository = asennuspaikanTyyppiRepository;
        this.anturiRepository = anturiRepository;
        this.tunnusarvotRepository = tunnusarvotRepository;
        this.mittausMapper = mittausMapper;
    }

    //TODO: This should be refactored someway to make it prettier :)
    @Transactional
    public Mittaus saveMittaus(Mittaus mittausRequest) {
        Mittaus mittaus = new Mittaus(
                mittausRequest.getAlkuaika(),
                mittausRequest.getLoppuaika(),
                mittausRequest.getMittaus_asianhallinta_id(),
                mittausRequest.getPdf_raportin_linkki(),
                mittausRequest.getRakennuksen_pinta_ala(),
                mittausRequest.getPerustamistapa(),
                mittausRequest.getJulkisivumateriaali(),
                mittausRequest.getRunkomateriaali(),
                mittausRequest.getRakennusvuosi(),
                mittausRequest.getKatuosoite(),
                mittausRequest.getPostinumero(),
                mittausRequest.getCreated_by_lx()
        );

        Mittaus savedMittaus = this.mittausRepository.save(mittaus);


        for (AsennettuAnturi asennettuAnturiRequest : mittausRequest.getAsennettuAnturiSet()) {
            AsennettuAnturi asennettuAnturi = new AsennettuAnturi(
                    asennettuAnturiRequest.getMalli(),
                    asennettuAnturiRequest.getGpsLat(),
                    asennettuAnturiRequest.getGpsLong(),
                    asennettuAnturiRequest.getEtaisyysRadastaJosEri(),
                    asennettuAnturiRequest.getKerros(),
                    asennettuAnturiRequest.getSijoituspaikanLisaselite()
            );

            AsennuspaikanTyyppi asennuspaikanTyyppi = asennettuAnturiRequest.getAsennuspaikanTyyppi();
            AsennuspaikanTyyppi savedAsennuspaikanTyyppi =
                    this.asennuspaikanTyyppiRepository.save(asennuspaikanTyyppi);

            LOG.info("savedAsennuspaikanTyyppi " + savedAsennuspaikanTyyppi);

            asennettuAnturi.setAsennuspaikanTyyppi(savedAsennuspaikanTyyppi);
            asennettuAnturi.setMittaus(mittaus);
            AsennettuAnturi savedAnturi = this.anturiRepository.save(asennettuAnturi);

            for (AnturikohtaisetTunnusarvot tunnusarvotRequest :
                    asennettuAnturiRequest.getAnturikohtaisetTunnusarvotSet()) {

                AnturikohtaisetTunnusarvot anturikohtaisetTunnusarvot = new AnturikohtaisetTunnusarvot(
                        tunnusarvotRequest.getMittaussuunta_xyz(),
                        tunnusarvotRequest.getTarinan_maksimiarvo(),
                        tunnusarvotRequest.getHallitseva_taajuus(),
                        tunnusarvotRequest.getTarinan_tunnusluku_vw95_rms()
                );
                anturikohtaisetTunnusarvot.setAsennettuAnturi(savedAnturi);
                this.tunnusarvotRepository.save(anturikohtaisetTunnusarvot);
            }

            LOG.info("asennettuAnturi " + asennettuAnturi);
        }

        return savedMittaus;


    }

    @Transactional
    public List<Mittaus> getMittausList() {
        return mittausRepository.findAll();
    }

    @Transactional
    public List<Mittaus> searchMittausListByKeyword(MittausSearchParameters params) throws Exception {
        System.out.println("params?? " + params.toString());
        try {
            Specification<Mittaus> specification1 = MittausSpecifications.mittausKeywordLike(params.getSearchKeyword());
            Specification<Mittaus> specification2 = MittausSpecifications.mittausSquareArea(params);
            Specification<Mittaus> specification3 = MittausSpecifications.mittausConstructionYear(params);

            // TODO - FIX THIS
            /*
            Specification<Mittaus> specification = Specification
                    .where(specification1)
                    .and(specification2)
                    .and(specification3);
            */
            Specification<Mittaus> specification = specification1;
            return mittausRepository.findAll(specification);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Mittaus getMittaus(Integer id) {

        Mittaus mittaus = mittausRepository.findOne(id);
        return mittaus;
    }

    @Transactional
    public void deleteMittaus(Integer id) {
        boolean exists = mittausRepository.exists(id);
        if (!exists) {
            throw new IllegalStateException("Mittaus with id " + id + " doesn't exist");
        } else {
            mittausRepository.delete(id);
        }
    }

    @Transactional
    public Mittaus updateMittaus(MittausDto dto) throws IllegalStateException {
        Mittaus mittaus = getMittaus(dto.getKohde_id());
        mittausMapper.updateMittausFromDto(dto, mittaus);
        // add missing references to parent objects
        for (AsennettuAnturi asennettuAnturi : mittaus.getAsennettuAnturiSet()) {
            if (asennettuAnturi.getMittaus() == null) {
                asennettuAnturi.setMittaus(mittaus);
            }
            for (AnturikohtaisetTunnusarvot anturikohtaisetTunnusarvot : asennettuAnturi.getAnturikohtaisetTunnusarvotSet()) {
                if (anturikohtaisetTunnusarvot.getAsennettuAnturi() == null) {
                    anturikohtaisetTunnusarvot.setAsennettuAnturi(asennettuAnturi);
                }
            }
        }
        mittausRepository.save(mittaus);
        return mittaus;
    }
}
