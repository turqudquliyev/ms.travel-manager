package az.ingress.service.concrete;

import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.repository.TourRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.service.abstraction.TourHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.exception.ErrorMessage.TOUR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TourHistoryServiceHandler implements TourHistoryService {
    private final TourRepository tourRepository;

    @Override
    public TourEntity findById(Long id) {
        return tourRepository.findById(id)
                             .orElseThrow(() -> new NotFoundException(TOUR_NOT_FOUND, id));
    }
}