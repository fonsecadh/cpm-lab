package player;

import java.io.File;

public class MyFile {
	
	File f = null;
	

	public MyFile (File file) {
		this.f = file;
	}
	
	public File getF() {
		return f;
	}	
	
	@Override
	public String toString() {
		return f.getName();
	}

}
