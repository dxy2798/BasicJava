package Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class testFileExist {

    public static InputStream getStreamFromFile(File file) throws Exception {
        InputStream stream = null;
        try {
            if (!file.exists()) {
                throw new Exception("file " + file + " doesn't exist");
            }
            if (file.isDirectory()) {
                throw new Exception("file " + file + " is a directory");
            }
            stream = new FileInputStream(file);

        } catch (Exception e) {
            throw new Exception("couldn't access file " + file + ": " + e.getMessage());
        }
        return stream;
    }
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\dbeaver\\dbeaver.exe");
		System.out.println(getStreamFromFile(file));
	}

}
