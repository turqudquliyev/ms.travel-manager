package az.ingress.mapper;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.model.dto.TravelerDto;
import az.ingress.model.request.AddTravelerRequest;

import java.util.List;
import java.util.Set;

public enum TravelerMapper {
    TRAVELER_MAPPER;

    public TravelerEntity buildTravelerEntity(AddTravelerRequest travelerRequest) {
        return TravelerEntity.builder()
                             .firstName(travelerRequest.getFirstName())
                             .lastName(travelerRequest.getLastName())
                             .email(travelerRequest.getEmail())
                             .build();
    }

    public List<TravelerDto> toTravelerDtoList(Set<TravelerEntity> travelers) {
        return travelers.stream()
                        .map(TRAVELER_MAPPER::toTravelerDto)
                        .toList();
    }

    private TravelerDto toTravelerDto(TravelerEntity travelerEntity) {
        return TravelerDto.builder()
                          .firstName(travelerEntity.getFirstName())
                          .lastName(travelerEntity.getLastName())
                          .email(travelerEntity.getEmail())
                          .build();
    }
}