package az.ingress.model.request;

import az.ingress.model.dto.PassportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuideRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private PassportDto passport;
}