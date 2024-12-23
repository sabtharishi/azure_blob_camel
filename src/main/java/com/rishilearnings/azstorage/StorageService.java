package com.rishilearnings.azstorage;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//@Service
public class StorageService {

    @Autowired
    private BlobContainerClient blobContainerClient;


    public List<String> listFiles() {

        return blobContainerClient.listBlobs()
                .stream()
                .map(BlobItem::getName)
                .toList();

    }


    /**
     * Read the specified file
     * @param fileName - FileName to read
     *
     * @return - file content
     */
    public byte[] getFile(String fileName) {

        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        return blobClient.downloadContent().toBytes();

    }


    /**
     * Upload String content as blob
     *
     * @param blobName
     * @param value
     * @return
     */
    public List<String> uploadDataToBlob(String blobName, String value) {
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        blobClient.upload(BinaryData.fromString(value));

        return listFiles();
    }


    /**
     * Upload file as blob
     *
     * @param file
     * @return
     * @throws IOException
     */
    public List<String> upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream());

        return listFiles();
    }


}
