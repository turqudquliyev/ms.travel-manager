package az.ingress.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassportDto {
    private String country;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String passportNumber;
}