package com.rishilearnings.azstorage.routes;

import com.azure.storage.blob.models.ListBlobContainersOptions;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.azure.storage.blob.BlobConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class BlobConsumerRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json);


        // Read specific given blob
//        from("azure-storage-blob://rishiblobstorage2/dump-container?blobName=test1.xml&credentialType=AZURE_SAS")
//            .log("Message received from blob storage..")
//            .log("Blob body ${body}")
//            .to("mock://blobdirectory");

        /**
         Read all blobs
        batch consumer, hence you can consume multiple blobs with only specifying the container name, the consumer will return multiple exchanges depending on the number of the blobs in the container.*/

//        from("azure-storage-blob://rishiblobstorage2/dump-container?credentialType=AZURE_SAS")
//                .log("Reading all blobs from a container..")
//                .log("${body}")
//                .to("mock://blobdirectory");


        //listBlobContainers

        from("direct:listContainer")
                .log("Reading all containers from storage account..")
                .process(exchange -> {
                    // set the header you want the producer to evaluate, refer to the previous
                    // section to learn about the headers that can be set
                    // e.g.:
                    exchange.getIn().setHeader(BlobConstants.LIST_BLOB_CONTAINERS_OPTIONS, new ListBlobContainersOptions().setMaxResultsPerPage(10));
                })
                .to("azure-storage-blob://rishiblobstorage2?operation=listBlobContainers&credentialType=AZURE_SAS")


                .split(body())
                    .log("container name: ${body.getName()}")
                .end()
                .to("mock:result");



        from("direct:getBlob")
                .log("Reading all containers from storage account..${exchangeProperty.containerName}")
                .toD("azure-storage-blob://rishiblobstorage2/${exchangeProperty.containerName}?blobName=${exchangeProperty.blobName}&operation=getBlob&credentialType=AZURE_SAS")
                .log("container name: ${body}");

        from("direct:createBlob")
                .log("createBlob Blob ${exchangeProperty.blobName} in container: ${exchangeProperty.containerName}")
                .toD("azure-storage-blob://rishiblobstorage2/${exchangeProperty.containerName}?blobName=${exchangeProperty.blobName}&operation=uploadBlockBlob&credentialType=AZURE_SAS")
                .log("container name: ${body}");

        from("direct:createBlobWithVirtualFolder")
                .log("createBlob Blob ${exchangeProperty.blobName} in container: ${exchangeProperty.containerName}")
                .toD("azure-storage-blob://rishiblobstorage2/${exchangeProperty.containerName}?blobName=${exchangeProperty.blobName}&operation=uploadBlockBlob&credentialType=AZURE_SAS")
                .log("container name: ${body}")
                .to("mock:result");
    }
}
