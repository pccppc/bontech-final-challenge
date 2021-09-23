package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateSilo{

	@NotNull(message = "id should not be null")
	private Long id;

	@NotNull(message = "name should not be null")
	private String name;

	@NotNull(message = "maximumCapacity should not be null")
	private Long maximumCapacity;

}
