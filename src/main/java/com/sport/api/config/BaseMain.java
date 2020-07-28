package com.sport.api.config;

import com.sport.api.utils.JFGlobalFunctions;
import com.sport.api.utils.JFServices;

public abstract class BaseMain {
	public static Boolean staticInitSuccessful = false;

	static {
		try {
			doStaticinit();
			staticInitSuccessful = true;
		} catch (Exception e) {
			staticInitSuccessful = false;
			e.printStackTrace();
		}
	}

	public static void doStaticinit() {
		try {
			if (!staticInitSuccessful) {
				JFGlobalFunctions.loadConfig("/com/sport/api/config/Config.cfg");
				JFServices.loadServices("/com/sport/api/config/Services.cfg");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
