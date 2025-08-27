package az.ingress.service.abstraction;

import az.ingress.dao.entity.TourEntity;

public interface TourHistoryService {

    TourEntity findById(Long id);
}