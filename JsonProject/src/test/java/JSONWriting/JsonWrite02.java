package JSONWriting;


import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class JsonWrite02 {
	
	public void insertdata(JSONObject obj, String Path) throws Exception {
		File f1 = new File(Path);
		FileWriter fw = new FileWriter(f1);
		fw.write(obj.toJSONString());
		fw.close();
		
	}
	
	
	@Test
	public void writedata()throws Exception{
		//JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject innerobj = new JSONObject();
		
		innerobj.put("FirstName", "Ashu");
		innerobj.put("LastName", "Khana");
		innerobj.put("Age", "24");
		arr.add(innerobj);
		innerobj.put("FirstName", "Aman");
		innerobj.put("LastName", "Yadav");
		innerobj.put("Age", "27");
		
		arr.add(innerobj);
		String Path= "src\\test\\java\\JSONWriting\\data2.json";
		innerobj.clear();
		innerobj.put("Employee",arr);
		
		insertdata(innerobj,Path);
		Path= "src\\test\\java\\JSONWriting\\data1.yml";
		insertdata(innerobj,Path);
		
		//System.out.println(obj);
	}

}
