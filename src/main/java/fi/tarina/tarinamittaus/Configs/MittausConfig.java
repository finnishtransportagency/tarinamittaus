package fi.tarina.tarinamittaus.Configs;

//import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
//import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
//import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
//import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
//import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
//import fi.tarina.tarinamittaus.Model.Mittaus;
//import fi.tarina.tarinamittaus.Repository.AnturiRepository;
//import fi.tarina.tarinamittaus.Repository.AnturikohtaisetTunnusarvotRepository;
//import fi.tarina.tarinamittaus.Repository.MittausRepository;
//import fi.tarina.tarinamittaus.Repository.AsennuspaikanTyyppiRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//
//@Configuration
//public class MittausConfig implements WebMvcConfigurer {
//
//    private final Logger LOG = LoggerFactory.getLogger(MittausConfig.class);
//
//
//    CommandLineRunner commandLineRunner(AsennuspaikanTyyppiRepository asennuspaikanTyyppiRepository,
//                                        MittausRepository mittausRepository,
//                                        AnturiRepository anturiRepository,
//                                        AnturikohtaisetTunnusarvotRepository tunnusarvotRepository) {
//        return args -> {
//            AsennuspaikanTyyppi paikka = new AsennuspaikanTyyppi(
//                    "maa",
//                    "lisätiedot"
//            );
//            Mittaus m1 = new Mittaus(
//                    Timestamp.valueOf(LocalDateTime.now()),
//                    Timestamp.valueOf(LocalDateTime.now()),
//                    "MITTAUS_ASIANHALLINTA_ID",
//                    "PDF_RAPORTIN_LINKKI",
//                    50.0,
//                    "PERUSTAMISTAPA",
//                    "JULKISIVUMATERIAALI",
//                    "RUNKOMATERIAALI",
//                    Integer.valueOf(1976),
//                    "KATUOSOITE",
//                    "00750",
//                    "CREATED_BY_LX"
//            );
//            AsennettuAnturi as1 = new AsennettuAnturi(
//                    "malli1",
//                    45.5,
//                    75.5,
//                    3.0,
//                    2,
//                    "selite"
//            );
//            AnturikohtaisetTunnusarvot tunnusarvot = new AnturikohtaisetTunnusarvot(
//                    'X',
//                    45.6,
//                    500.0,
//                    75.4
//            );
//
//            as1.setAsennuspaikanTyyppi(paikka);
//            as1.setMittaus(m1);
//
//            tunnusarvot.setAsennettuAnturi(as1);
//
//            LOG.info("paikka " + paikka);
//            LOG.info("anturi configissa" + as1);
//            LOG.info("mittaus configissa" + m1);
//
//            mittausRepository.save(m1);
//
//            asennuspaikanTyyppiRepository.save(paikka);
//            anturiRepository.save(as1);
//
//            tunnusarvotRepository.save(tunnusarvot);
//
//        };
//    }
//}
