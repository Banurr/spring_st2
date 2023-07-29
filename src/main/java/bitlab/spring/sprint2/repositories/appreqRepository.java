package bitlab.spring.sprint2.repositories;

import bitlab.spring.sprint2.models.ApplicationRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface appreqRepository extends JpaRepository<ApplicationRequest,Long> {
    @Query("select i from ApplicationRequest i where i.handled = :filter")
    List<ApplicationRequest> getFiltered(boolean filter);

    @Transactional
    @Modifying
    @Query("UPDATE ApplicationRequest a SET a.handled = true WHERE a.id = :id")
    void editRequest(@Param("id") Long id);
}
