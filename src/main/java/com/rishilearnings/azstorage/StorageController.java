package com.rishilearnings.azstorage;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/storages/containers")
public class StorageController {

    @Autowired
    private ProducerTemplate template;

    @GetMapping
    public Exchange listContainers() {

        return template.send("direct:listContainer", exchange -> {
        });
    }

    @GetMapping(value = "/{containerName}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getBlob(@PathVariable String containerName,
                          @RequestParam String blobName) {

        return template.send("direct:getBlob", exchange ->
        {
            exchange.setProperty("blobName", blobName);
            exchange.setProperty("containerName", containerName);
        }).getIn().getBody(String.class);
    }

    @PostMapping(value = "/{containerName}/{blobName}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String createBlob(
            @PathVariable String containerName,
            @PathVariable String blobName,
            @RequestBody String body) {

        return template.send("direct:createBlob", exchange ->
        {
            exchange.setProperty("blobName", blobName);
            exchange.setProperty("containerName", containerName);
            exchange.getIn().setBody(body);
        }).getIn().getBody(String.class);
    }


    @PutMapping("/{containerName}")
    public String createBlobWithVirtualFolder(
            @PathVariable String containerName,
            @RequestParam("blobName") String blobName,
            @RequestBody String body) {

        return template.send("direct:createBlobWithVirtualFolder", exchange ->
        {
            exchange.setProperty("blobName", blobName);
            exchange.setProperty("containerName", containerName);
            exchange.getIn().setBody(body);
        }).getIn().getBody(String.class);
    }


}
