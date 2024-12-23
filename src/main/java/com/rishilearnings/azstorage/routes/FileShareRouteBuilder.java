package com.rishilearnings.azstorage.routes;

import com.azure.storage.blob.models.ListBlobContainersOptions;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.azure.storage.blob.BlobConstants;
import org.apache.camel.component.file.azure.FilesHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileShareRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {


//        from("azure-files://rishifilestorage/fuse/payload?move=.done&credentialType=AZURE_SAS&sv=2022-11-02&ss=bfqt&srt=sco&sp=rwdlacupiytfx&se=2025-01-03T20:23:12Z&st=2024-12-23T12:23:12Z&spr=https&sig=5sjyQAW72qYqjFsWKk17xzR45ZJHd0axN5hySas7RXA%3D")
//                .log("new File found:: ${body}");



        // triggered when a new file is placed to given folder (not inside its subfolder)

        from("azure-files://rishifilestorage/fuse/payload?move=.done&credentialType=SHARED_ACCOUNT_KEY&sharedKey=5ah278OtEEXSTINjQZVB5sdd68biJEeTTFMseVs46UO1jbRNvAfL5NS0K3akJhH2hVGHqDjT/3ud+ASthBPedw==")
                .log("new File found:: ${body}");


        // triggered when a new file is placed to given folder (not inside its subfolder)

        from("azure-files://rishifilestorage/fuse/payload/claims?move=.done&credentialType=SHARED_ACCOUNT_KEY&sharedKey=5ah278OtEEXSTINjQZVB5sdd68biJEeTTFMseVs46UO1jbRNvAfL5NS0K3akJhH2hVGHqDjT/3ud+ASthBPedw==")
                .log("new File under claims folder found:: ${body}")
                        .setHeader(FilesHeaders.FILE_NAME, constant("from_customer_1.xml"))
                .setProperty("transactionId", UUID::randomUUID)
                .toD("azure-files://rishifilestorage/fuse/dump/${exchangeProperty.transactionId}?credentialType=SHARED_ACCOUNT_KEY&sharedKey=5ah278OtEEXSTINjQZVB5sdd68biJEeTTFMseVs46UO1jbRNvAfL5NS0K3akJhH2hVGHqDjT/3ud+ASthBPedw==")
        ;




    }

}




