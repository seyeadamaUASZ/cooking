package com.sid.gl.tools;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse {
	private HttpStatus httpStatus;
	private String messageDebug;
	private Object object;

}
