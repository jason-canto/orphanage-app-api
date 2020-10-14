package com.canto.happy.model;

import org.bson.types.Binary;

import lombok.Data;

@Data
public class Photo {
	private final String title;
	private final Binary image;
}
