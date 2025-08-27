package az.ingress.dao.repository;

import az.ingress.dao.entity.GuideEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GuideRepository extends CrudRepository<GuideEntity, Long> {

    @EntityGraph(attributePaths = "tours")
    Optional<GuideEntity> findWithToursById(Long id);

    Page<GuideEntity> findAll(Pageable pageable);
}