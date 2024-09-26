package com.scalefocus.digitalriver.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientRequestDto {

	@NotNull
	@NotEmpty
	private String name;

}
