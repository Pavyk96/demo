package denis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemRequestDto {

    @NotBlank
    private String name;
}
