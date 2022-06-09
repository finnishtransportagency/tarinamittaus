package fi.tarina.tarinamittaus.Repository;

import fi.tarina.tarinamittaus.Model.AsennettuAnturi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnturiRepository extends JpaRepository<AsennettuAnturi, Integer> {

}
