package com.example.store;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import com.example.config.properties.StorageProperties;
import com.example.config.service.OSSService;
import com.example.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.util.Base64Util;
import com.example.util.FileNameUtil;


@Service
public class FileSystemStorageService implements StorageService {

	@Autowired
	private StorageProperties storageProperties;
	
	@Autowired
	private OSSService ossService;

	@Override
	public void init(Path path) {
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			throw new StorageException("Initialize Store Fail", e);
		}
	}


	@Override
	public String store(MultipartFile file, Path path) {
		String fileURL = null;
		if (file.isEmpty()) {
			throw new StorageException("Please choose the file");
		}
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			throw new StorageException("File format not correct");
		}
		storageProperties.init();
		if(storageProperties.getUploadType().equals("45")) {
			fileURL = ossService.uploadFile2OSS(file, path.toString());
		}else {
			path = Paths.get(storageProperties.getUploadFiledir() + path.toString());
			if (!Files.exists(path)) {
				init(path);
			}
			String newFilename = FileNameUtil.getFileName(filename);
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, path.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
				fileURL = storageProperties.getStaticUrl() + newFilename;
			} catch (IOException e) {
				throw new StorageException("File upload error", e);
			}
		}
		return fileURL;
	}
	
	@Override
	public String store(String base64, Path path) {
		String _base64 = base64.substring(base64.indexOf(",") + 1, base64.length());
		byte[] content = Base64Util.decode2(_base64);
		String header = base64.split(";")[0];
		return store(new BASE64DecodedMultipartFile(content,header),path);
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
