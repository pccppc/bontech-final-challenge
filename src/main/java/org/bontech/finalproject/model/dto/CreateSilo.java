package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CreateSilo{

	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "maximumCapacity should not be null")
	private Long maximumCapacity;

}
