package de.mag.hypercab.api.filesystem;

import java.io.InputStream;
import java.util.List;

public interface FileSystemCRUDService {

	public List<String> getFiles();

	public void addFile(InputStream fileStream, String fileName);

	public void removeFile(String fileName);

	public void renameFile(String originalFileName, String newFileName);

}
