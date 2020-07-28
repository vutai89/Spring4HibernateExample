package com.sport.api.utils;

import fpt.tpb.ebank.dictionary.Dictionary;
import fpt.tpb.ebank.utils.AppException;

public class JFServices {
	public static Dictionary dic = new Dictionary();

	public JFServices() {

	}

	public static void loadServices(String p_services_file_name) throws AppException {

		try {

			// servicesCodeArray = loadMandatory("ServicesCode").split(",");

			dic.load(JFServices.class

					.getResourceAsStream(p_services_file_name));

		} catch (Exception e) {

			System.out.println("loadServices : " + e.toString());

			// throw new AppException(e, "RequestProcessor.fillParameter");

		}

	}
}
