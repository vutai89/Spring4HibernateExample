package com.sport.api.input;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
	public String serviceCode;
	public String stepId;
	public Map<String, Object> info;
}
