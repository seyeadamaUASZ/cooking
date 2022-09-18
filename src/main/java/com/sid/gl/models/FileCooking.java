package com.sid.gl.models;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileCooking {
	private String fileName;
	private String fileType;
	private String fileDownloadUri;

}
