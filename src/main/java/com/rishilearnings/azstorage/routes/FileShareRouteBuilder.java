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


        // triggered when a new file is placed to given folder (not inside its subfolder)

        from("azure-files://rishifilestorage/fuse/payload?move=.done")
                .log("new File found:: ${body}");


        // triggered when a new file is placed to given folder (not inside its subfolder)

        from("azure-files://rishifilestorage/fuse/payload/claims?move=.done")
                .log("new File under claims folder found:: ${body}")
                        .setHeader(FilesHeaders.FILE_NAME, constant("from_customer_1.xml"))
                .setProperty("transactionId", UUID::randomUUID)
                .toD("azure-files://rishifilestorage/fuse/dump/${exchangeProperty.transactionId}")
        ;




    }

}




