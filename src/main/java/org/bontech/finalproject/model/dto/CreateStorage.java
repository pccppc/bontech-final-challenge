package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateStorage{

	@NotNull(message = "name should not be null")
	private String name;

}
