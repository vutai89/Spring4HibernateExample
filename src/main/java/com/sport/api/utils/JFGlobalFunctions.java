package com.sport.api.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import fpt.tpb.ebank.dictionary.Dictionary;
import fpt.tpb.ebank.dictionary.DictionaryNode;

public class JFGlobalFunctions {
	public static Dictionary dic = new Dictionary();

	public static HashMap<String, String> SYS_DATA_COLLECTION_JSON = new HashMap<String, String>();

	public static DictionaryNode getDictionaryNode(String node) {
		return dic.getNode(node);
	}

	public static void loadConfig(String strFileName) throws Exception {
		dic.load(JFGlobalFunctions.class.getResourceAsStream(strFileName));
	}

	public static String getDateTimeNow() {
		DateFormat dateFormat = newSimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	private static DateFormat newSimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getConfigValue(String nodePath) {
		return dic.getString(nodePath);
	}

	public static String getStringFromQS(JSONObject p_field_table, String p_field_name, String p_default)
			throws Exception {
		try {
			String l_field_name_tmp = "";
			String l_field_value = "";
			Iterator p_keys_table = p_field_table.keys();
			while (p_keys_table.hasNext()) {
				l_field_name_tmp = (String) p_keys_table.next();
				if (l_field_name_tmp.equals(p_field_name)) {
					l_field_value = p_field_table.getString(p_field_name);
					break;
				}
			}

			if (l_field_value == null || l_field_value.length() == 0 || l_field_value.equals("null")) {
				if (p_default != null) {
					return p_default;
				}
				return null;
			}
			return l_field_value;

		} catch (Exception e) {
			return p_default;
		}

	}

	public static Map<String, String> getMapStringFromQS(JSONObject request, String fieldName) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String mapString = getStringFromQS(request, fieldName, "{}");
			JSONObject object = new JSONObject(mapString);
			Iterator<?> keys = object.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = object.getString(key);
				map.put(key, value);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}

	}

}
