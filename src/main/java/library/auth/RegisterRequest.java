package library.auth;

import jakarta.validation.constraints.*;
import library.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank
  @NotNull
  private String firstname;
  @NotBlank
  @NotNull
  private String lastname;
  @Email
  private String email;
  @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
  private String phone;
  @NotBlank(message = "Password is required")
  @NotNull(message = "Password cannot be null")
  @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
  private String password;


}
