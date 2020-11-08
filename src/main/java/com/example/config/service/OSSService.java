package com.example.config.service;

import java.io.IOException;
import java.io.InputStream;

import com.example.exception.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.example.config.properties.StorageProperties;
import com.example.util.FileNameUtil;


@Component
public class OSSService implements BaseService<OSSClient>{

	private Logger log = LoggerFactory.getLogger(OSSService.class);
	
	@Autowired
	private StorageProperties storageProperties;
	
	private OSSClient ossClient;
	
	/**
	 * initial OSSClient
	 */
	@Override
	public OSSClient instance() {
		storageProperties.init();
		if(ossClient != null) {
			return ossClient;
		}
		ossClient = new OSSClient(storageProperties.getEndpoint(), storageProperties.getAccessKeyId(),storageProperties.getAccessKeySecret());
		return ossClient;
	}


	public String uploadFile2OSS(MultipartFile file,String customPath) {
		if (file.isEmpty()) {
			throw new StorageException("请选择要上传的文件");
		}

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			throw new StorageException("文件格式不正确");
		}
		String newFilename = FileNameUtil.getFileName(filename);
		try {

			uploadFile2OSS(file.getInputStream(),customPath + "/" + newFilename);
		} catch (IOException e) {
			log.error("图片上传OSS失败",e);
		}
		return storageProperties.getStaticUrl() + storageProperties.getOssFiledir() + customPath + "/" + newFilename;
	}

	public String uploadFile2OSS(InputStream instream, String path) {
		String ret = "";
        try {
        	ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
	        objectMetadata.setHeader("Pragma", "no-cache");
	        objectMetadata.setContentType(getcontentType(path.substring(path.lastIndexOf("."))));
	        objectMetadata.setContentDisposition("inline;filename=" + path);
	        // upload
	        PutObjectResult putObject = instance().putObject(storageProperties.getBucketName(),storageProperties.getOssFiledir() + path,instream,objectMetadata);
	        ret = putObject.getETag();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}finally {
			try {
				if(instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
        return ret;
	}

	public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}
