package az.ingress.dao.repository;

import az.ingress.dao.entity.TravelerEntity;
import org.springframework.data.repository.CrudRepository;

public interface TravelerRepository extends CrudRepository<TravelerEntity, Long> {
}