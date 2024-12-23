package com.rishilearnings.azstorage;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Slf4j
//@Configuration
public class AzureConfiguration {


    @Value("${azure.blob.connection.string}")
    private String azureBlobConnectionString;

    @Value("${azure.blob.container.name}")
    private String azureBlobContainerName;


    @Bean
    public BlobServiceClient blobServiceClient() {


        return new BlobServiceClientBuilder()
                .connectionString(azureBlobConnectionString)
                .buildClient();

    }


    @Bean
    public BlobContainerClient blobContainerClient() {

        return blobServiceClient().getBlobContainerClient(azureBlobContainerName);


    }

}
