package Data;

import Components.Project;
import Model.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class WriteData {
	public WriteData(){

	}

	public static void writeDown(String path, ArrayList<Project> map){
		File newFolder = new File(path);
		if (!newFolder.exists()) {

			boolean success = newFolder.mkdirs();

			if (success) {
				System.out.println("Success!");
			} else {
				System.out.println("Fail");
			}
		}

		path = path + "/data.json";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			objectMapper.writeValue(new File(path), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
