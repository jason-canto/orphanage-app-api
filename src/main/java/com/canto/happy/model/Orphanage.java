package com.canto.happy.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Orphanage {

	@Id
	private String id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Description is mandatory")
	private String description;

	@NotBlank(message = "Instructions is mandatory")
	private String instructions;

	@NotBlank(message = "OpeningHours is mandatory")
	private String openingHours;

	@NotNull(message = "Latitude is mandatory")
	private Double latitude;

	@NotNull(message = "Longitude is mandatory")
	private Double longitude;

	private boolean openOnWeekend;

	private List<Photo> images;
}
