package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateGuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.model.response.PageableResponse;

public interface GuideService {

    void createGuide(CreateGuideRequest guideRequest);

    void assignTour(Long id, Long tourId);

    PageableResponse<GuideResponse> getGuides(PageCriteria pageCriteria);
}