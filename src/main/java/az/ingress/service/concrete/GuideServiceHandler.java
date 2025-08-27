package az.ingress.service.concrete;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.repository.GuideRepository;
import az.ingress.exception.ConflictException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateGuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.model.response.PageableResponse;
import az.ingress.service.abstraction.GuideService;
import az.ingress.service.abstraction.TourHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static az.ingress.exception.ErrorMessage.GUIDE_NOT_FOUND;
import static az.ingress.exception.ErrorMessage.TOUR_DATE_CONFLICT;
import static az.ingress.mapper.GuideMapper.GUIDE_MAPPER;
import static az.ingress.mapper.PageableMapper.PAGEABLE_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {
    private final GuideRepository guideRepository;
    private final TourHistoryService tourHistoryService;

    @Override
    public void createGuide(CreateGuideRequest guideRequest) {
        log.info("ActionLog.createGuide.start - {}", guideRequest);
        var guide = GUIDE_MAPPER.buildGuideEntity(guideRequest);
        guideRepository.save(guide);
        log.info("ActionLog.createGuide.end");
    }

    @Override
    public void assignTour(Long id, Long tourId) {
        log.info("ActionLog.assignTour.start - {} & {}", id, tourId);
        var tour = tourHistoryService.findById(tourId);
        var guide = guideRepository.findWithToursById(id)
                                   .orElseThrow(() -> new NotFoundException(GUIDE_NOT_FOUND, id));

        ensureGuideAvailability(guide, tour);

        GUIDE_MAPPER.addNewTour(guide, tour);
        guideRepository.save(guide);
        log.info("ActionLog.assignTour.end");
    }

    @Override
    public PageableResponse<GuideResponse> getGuides(PageCriteria pageCriteria) {
        log.info("ActionLog.getGuides.start - {}", pageCriteria);
        var guides = guideRepository.findAll(PAGEABLE_MAPPER.toPageable(pageCriteria));
        var pageableGuideResponse = PAGEABLE_MAPPER.toPageableResponse(guides, GUIDE_MAPPER::toGuideResponse);
        log.info("ActionLog.getGuides.end - {}", pageableGuideResponse);
        return pageableGuideResponse;
    }

    private void ensureGuideAvailability(GuideEntity guide, TourEntity tour) {
        var startDate = tour.getStartDate();
        var endDate = tour.getEndDate();
        var dateConflict = guide.getTours().stream()
                                           .anyMatch(it -> startDate.isBefore(it.getEndDate()) && endDate.isAfter(it.getStartDate()));

        if (dateConflict) {
            throw new ConflictException(TOUR_DATE_CONFLICT, startDate, endDate);
        }
    }
}