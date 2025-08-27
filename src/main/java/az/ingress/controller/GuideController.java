package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateGuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.model.response.PageableResponse;
import az.ingress.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/guides")
public class GuideController {
    private final GuideService guideService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createGuide(@RequestBody CreateGuideRequest guideRequest) {
        guideService.createGuide(guideRequest);
    }

    @GetMapping
    public PageableResponse<GuideResponse> getGuides(PageCriteria pageCriteria) {
        return guideService.getGuides(pageCriteria);
    }

    @PatchMapping("{id}/tours/{tourId}")
    @ResponseStatus(NO_CONTENT)
    public void assignTour(@PathVariable Long id, @PathVariable Long tourId) {
        guideService.assignTour(id, tourId);
    }
}