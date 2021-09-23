package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateStorage{

	@NotNull(message = "id should not be null")
	private Long id;

	@NotNull(message = "name should not be null")
	private String name;

}
