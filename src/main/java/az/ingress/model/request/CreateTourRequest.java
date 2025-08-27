package az.ingress.model.request;

import az.ingress.model.dto.DestinationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTourRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private LocalDate endDate;
    private LocalDate startDate;
    private List<DestinationDto> destinations;
}