package az.ingress.dao.repository;

import az.ingress.dao.entity.PassportEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<PassportEntity, Long> {
}