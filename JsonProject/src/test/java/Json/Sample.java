package Json;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Sample {

	public static List result = new ArrayList();
	public static int i = 0;
	static Object value;

	public static Object getvalue(JSONObject obj, String key, int index) {
		i = 0;
		getKey(obj, key, index);
		return value;
	}

	public static void pareseObject(JSONObject obj, String key) {
		// System.out.println(obj.has(key));
		System.out.println(obj.get(key));
	}

	public static void getKey(JSONObject obj, String key, int index) {
		boolean check = obj.has(key);
		Iterator keys;
		String nextkeys;

		if (check == false) {
			keys = obj.keys();
			while (keys.hasNext()) {
				nextkeys = (String) keys.next();
//				System.out.println(nextkeys);
//				System.out.println(obj.get(nextkeys).getClass().getSimpleName());
//				System.out.println("Object: " + (obj.get(nextkeys) instanceof JSONObject));
//				System.out.println("Array: " + (obj.get(nextkeys) instanceof JSONArray));
				if (obj.get(nextkeys) instanceof JSONObject) {
					if (check == false) {
//						System.out.println(obj.getJSONObject(nextkeys));
						getKey(obj.getJSONObject(nextkeys), key, index);
					}
				} else if (obj.get(nextkeys) instanceof JSONArray) {
//					System.out.println("ArrayField");
					JSONArray jarray = obj.getJSONArray(nextkeys);
//					System.out.println(jarray);
					for (int i = 0; i < jarray.length(); i++) {
						if ((jarray.get(i) instanceof JSONObject)) {
							String str1 = jarray.get(i).toString();
							// System.out.println(str1);
							JSONObject obj1 = new JSONObject(str1);
							// System.out.println(jarray.get(i));
							if (check == false) {
								// System.out.println();
								getKey(obj1, key, index);
							}
						}
					}
				}
			}
		} else {
			// pareseObject(obj, key);
			if (i == index)
				value = obj.get(key);
			i++;
		}

	}

	public static void main(String[] args) throws Throwable {
		FileReader fr = new FileReader("src/test/java/Data/data5.json");
		Object obj = new JSONParser().parse(fr);
		JSONObject jobj= new JSONObject();
		if(obj instanceof org.json.simple.JSONObject)
			jobj = new JSONObject(obj.toString());
		if(obj instanceof org.json.simple.JSONArray) {
			JSONArray jarr = new JSONArray(obj.toString());
			jobj= jarr.getJSONObject(0);
		}
		System.out.println(jobj);
		System.out.println(getvalue(jobj, "number", 0));
		System.out.println(getvalue(jobj, "lastName", 0));
	}
}
