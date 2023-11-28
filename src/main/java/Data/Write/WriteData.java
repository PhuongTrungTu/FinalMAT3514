package Data.Write;


import Components.ProjectManager;
import Model.HashMap;
import Model.Node.MapNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteData {
	public static String convertHashMapToJson(HashMap<Object> hashMap) {
		StringBuilder jsonBuilder = new StringBuilder("{");

		for (int i = 0; i < hashMap.size(); i++) {
			if (jsonBuilder.length() > 1) {
				jsonBuilder.append(",");
			}
			MapNode<Object> entry = hashMap.get(i);
			jsonBuilder.append("\"").append(entry.getField()).append("\":");

			Object value = entry.getData();
			if (value instanceof String) {
				jsonBuilder.append("\"").append(value).append("\"");
			} else {
				jsonBuilder.append(value);
			}
		}

		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}
	public WriteData(){
	}

	public static void writeDown(String path,HashMap<Object> map){
		File thuMucMoi = new File(path);
		if (!thuMucMoi.exists()) {

			boolean success = thuMucMoi.mkdirs();

			if (success) {
				System.out.println("Success!");
			} else {
				System.out.println("Fail");
			}
		}

		String data = convertHashMapToJson(map);
		path = path + "/data.json";
		try {
			// Chuyển đổi nội dung thành mảng byte
			byte[] byteData = data.getBytes();

			// Sử dụng Files.write để ghi nội dung vào tập tin
			Files.write(Paths.get(path), byteData, StandardOpenOption.CREATE);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
