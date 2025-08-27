package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.AddTravelerRequest;
import az.ingress.model.request.CreateTourRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.TourDetailsResponse;
import az.ingress.model.response.TourResponse;

public interface TourService {

    void createTour(CreateTourRequest tourRequest);

    PageableResponse<TourResponse> getTours(PageCriteria pageCriteria);

    TourDetailsResponse getTour(Long id);

    void addTraveler(Long id, AddTravelerRequest travelerRequest);
}