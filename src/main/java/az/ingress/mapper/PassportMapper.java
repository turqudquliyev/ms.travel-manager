package az.ingress.mapper;

import az.ingress.dao.entity.PassportEntity;
import az.ingress.model.dto.PassportDto;

public enum PassportMapper {
    PASSPORT_MAPPER;

    public PassportEntity buildPassportEntity(PassportDto passport) {
        return PassportEntity.builder()
                             .passportNumber(passport.getPassportNumber())
                             .country(passport.getCountry())
                             .expiryDate(passport.getExpiryDate())
                             .issueDate(passport.getIssueDate())
                             .build();
    }
}