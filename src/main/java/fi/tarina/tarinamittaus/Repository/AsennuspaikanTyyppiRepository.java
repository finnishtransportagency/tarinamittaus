package fi.tarina.tarinamittaus.Repository;

import fi.tarina.tarinamittaus.Model.AsennuspaikanTyyppi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsennuspaikanTyyppiRepository extends JpaRepository<AsennuspaikanTyyppi, Integer> {

}
