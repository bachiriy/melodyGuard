package io.benfill.isdb.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
	@NotNull(message = "Username is required")
	@NotBlank(message = "Username is required")
	private String username;

	@NotNull(message = "Password is required")
	@NotBlank(message = "Password is required")
	private String password;
}
