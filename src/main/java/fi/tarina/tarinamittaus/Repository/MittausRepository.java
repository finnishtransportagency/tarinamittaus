package fi.tarina.tarinamittaus.Repository;


import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import fi.tarina.tarinamittaus.Model.Mittaus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MittausRepository extends JpaRepository<Mittaus, Integer>,
                                           JpaSpecificationExecutor<Mittaus> {

//    Double rakennuksen_pinta_ala, " ",Integer rakennusvuosi, " ",


    @Query("SELECT m from Mittaus m where CONCAT(m.mittaus_asianhallinta_id, ' ',m.pdf_raportin_linkki, ' " +
            "',m.perustamistapa, ' ',m.julkisivumateriaali, ' ',m.runkomateriaali, ' ',m.katuosoite, ' ',m.postinumero, ' ',m.created_by_lx) LIKE %?1%")
    List<Mittaus> search(String keyword);
}

