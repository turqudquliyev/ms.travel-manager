package az.ingress.service.concrete;

import az.ingress.dao.repository.TourRepository;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.AddTravelerRequest;
import az.ingress.model.request.CreateTourRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.TourDetailsResponse;
import az.ingress.model.response.TourResponse;
import az.ingress.service.abstraction.TourHistoryService;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static az.ingress.mapper.PageableMapper.PAGEABLE_MAPPER;
import static az.ingress.mapper.TourMapper.TOUR_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {
    private final TourRepository tourRepository;
    private final TourHistoryService tourHistoryService;

    @Override
    public void createTour(CreateTourRequest tourRequest) {
        log.info("ActionLog.createTour.start - {}", tourRequest);
        var tour = TOUR_MAPPER.buildTourEntity(tourRequest);
        tourRepository.save(tour);
        log.info("ActionLog.createTour.end");
    }

    @Override
    public PageableResponse<TourResponse> getTours(PageCriteria pageCriteria) {
        log.info("ActionLog.getTours.start - {}", pageCriteria);
        var tours = tourRepository.findAll(PAGEABLE_MAPPER.toPageable(pageCriteria));
        var pageableTourResponse = PAGEABLE_MAPPER.toPageableResponse(tours, TOUR_MAPPER::toTourResponse);
        log.info("ActionLog.getTours.end - {}", pageableTourResponse);
        return pageableTourResponse;
    }

    @Override
    public TourDetailsResponse getTour(Long id) {
        log.info("ActionLog.getTour.start - {}", id);
        var tour = tourRepository.findWithDestinationsAndTravelersById(id).orElseThrow();
        var tourResponse = TOUR_MAPPER.toTourDetailsResponse(tour);
        log.info("ActionLog.getTour.end - {}", tourResponse);
        return tourResponse;
    }

    @Override
    public void addTraveler(Long id, AddTravelerRequest travelerRequest) {
        log.info("ActionLog.addTraveler.start - {} & {}", id, travelerRequest);
        var tour = tourHistoryService.findById(id);
        TOUR_MAPPER.addNewTraveler(tour, travelerRequest);
        tourRepository.save(tour);
        log.info("ActionLog.addTraveler.end");
    }
}