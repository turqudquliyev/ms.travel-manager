package az.ingress.dao.repository;

import az.ingress.dao.entity.DestinationEntity;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepository extends CrudRepository<DestinationEntity, Long> {
}