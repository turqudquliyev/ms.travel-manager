package az.ingress.mapper;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.model.dto.DestinationDto;

import java.util.List;

public enum DestinationMapper {
    DESTINATION_MAPPER;

    public List<DestinationEntity> buildDestinations(List<DestinationDto> destinations, TourEntity tour) {
        return destinations.stream()
                           .map(it -> toDestinationEntity(it, tour))
                           .toList();
    }

    public List<DestinationDto> toDestinationList(List<DestinationEntity> destinations) {
        return destinations.stream()
                           .map(DESTINATION_MAPPER::toDestinationDto)
                           .toList();
    }

    private DestinationDto toDestinationDto(DestinationEntity destination) {
        return DestinationDto.builder()
                             .location(destination.getLocation())
                             .visitDate(destination.getVisitDate())
                             .description(destination.getDescription())
                             .build();
    }

    private DestinationEntity toDestinationEntity(DestinationDto destination, TourEntity tour) {
        return DestinationEntity.builder()
                                .location(destination.getLocation())
                                .visitDate(destination.getVisitDate())
                                .description(destination.getDescription())
                                .tour(tour)
                                .build();
    }
}