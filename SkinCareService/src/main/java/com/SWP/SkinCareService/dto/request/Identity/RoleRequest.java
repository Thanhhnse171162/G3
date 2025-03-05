package com.SWP.SkinCareService.dto.request.Identity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @NotBlank(message = "NOT_EMPTY")
    String name;
    @NotBlank(message = "NOT_EMPTY")
    String description;

}
