package de.mag.hypercab.api.filesystem;

import java.io.InputStream;
import java.util.Set;

public interface FileSystemCRUDService {

	public Set<String> getFiles();

	public void addFile(InputStream fileStream, String fileName);

	public void removeFile(String fileName);

	public void renameFile(String originalFileName, String newFileName);

}
