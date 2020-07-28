package com.sport.api.output;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {
	private String respCode;
	private String respContent;
	private T respObj;
	private List<T> respListObj;
}
