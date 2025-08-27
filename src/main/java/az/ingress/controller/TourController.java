package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.AddTravelerRequest;
import az.ingress.model.request.CreateTourRequest;
import az.ingress.model.response.PageableResponse;
import az.ingress.model.response.TourDetailsResponse;
import az.ingress.model.response.TourResponse;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/tours")
public class TourController {
    private final TourService tourService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createTour(@RequestBody CreateTourRequest tourRequest) {
        tourService.createTour(tourRequest);
    }

    @GetMapping
    public PageableResponse<TourResponse> getTours(PageCriteria pageCriteria) {
        return tourService.getTours(pageCriteria);
    }

    @GetMapping("{id}")
    public TourDetailsResponse getTour(@PathVariable Long id) {
        return tourService.getTour(id);
    }

    @PostMapping("{id}/travelers")
    @ResponseStatus(CREATED)
    public void addTraveler(@PathVariable Long id, @RequestBody AddTravelerRequest travelerRequest) {
        tourService.addTraveler(id, travelerRequest);
    }
}