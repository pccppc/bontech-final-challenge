package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteStorage{


	@NotNull(message = "id should not be null")
	private Long id;

}
