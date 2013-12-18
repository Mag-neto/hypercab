package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.InputStream;
import java.util.Set;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.filesystem.FileSystemCRUDService;

@Service
public class HyperPinFileSystemService implements FileSystemCRUDService {

	@Override
	public Set<String> getFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(InputStream fileStream, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFile(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameFile(String originalFileName, String newFileName) {
		// TODO Auto-generated method stub

	}

}
