package org.soneech.truereview.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
    @NotBlank(message = "Не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя должно содержать не менее 3 и не более 50 символов")
    String name,

    @NotBlank(message = "Не может быть пустым")
    @Size(min = 6, max = 320, message = "Почта должна содержать не менее 6 и не более 320 символов")
    @Email(message = "Не является корректной электронной почтой")
    String email,

    @NotBlank(message = "Не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    String password
) {
}
