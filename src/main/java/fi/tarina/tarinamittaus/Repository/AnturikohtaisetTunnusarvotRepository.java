package fi.tarina.tarinamittaus.Repository;

import fi.tarina.tarinamittaus.Model.AnturikohtaisetTunnusarvot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnturikohtaisetTunnusarvotRepository extends JpaRepository<AnturikohtaisetTunnusarvot, Integer> {
}
