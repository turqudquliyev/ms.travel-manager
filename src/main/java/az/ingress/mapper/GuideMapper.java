package az.ingress.mapper;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.model.request.CreateGuideRequest;
import az.ingress.model.response.GuideResponse;

import static az.ingress.mapper.PassportMapper.PASSPORT_MAPPER;

public enum GuideMapper {
    GUIDE_MAPPER;

    public GuideEntity buildGuideEntity(CreateGuideRequest guideRequest) {
        var passport = PASSPORT_MAPPER.buildPassportEntity(guideRequest.getPassport());
        var guide = GuideEntity.builder()
                               .name(guideRequest.getName())
                               .email(guideRequest.getEmail())
                               .phoneNumber(guideRequest.getPhoneNumber())
                               .build();
        guide.setPassport(passport);
        passport.setGuide(guide);
        return guide;
    }

    public GuideResponse toGuideResponse(GuideEntity guideEntity) {
        return GuideResponse.builder()
                            .id(guideEntity.getId())
                            .name(guideEntity.getName())
                            .email(guideEntity.getEmail())
                            .phoneNumber(guideEntity.getPhoneNumber())
                            .build();
    }

    public void addNewTour(GuideEntity guide, TourEntity tour) {
        guide.getTours().add(tour);
    }
}