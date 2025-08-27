package az.ingress.dao.repository;

import az.ingress.dao.entity.TourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TourRepository extends CrudRepository<TourEntity, Long> {

    @EntityGraph(attributePaths = {"destinations", "travelers"})
    Optional<TourEntity> findWithDestinationsAndTravelersById(@NonNull Long id);

    Page<TourEntity> findAll(Pageable pageable);
}