# azstorage_blob poc


## Blob Storage

-----

### Create new blob with virtual dir

```
curl --location --request PUT 'http://localhost:8080/storages/containers/dump-container?blobName=dump_123456%2Foriginal%2Frequest1.xml' \
--header 'Content-Type: text/plain' \
--data '<request1>
smple content
</request1>'
```

### Create new blob with given data

```
curl --location 'http://localhost:8080/storages/containers/dump-container/request5.xml' \
--header 'Content-Type: text/plain' \
--data '<request5>
smple content
</request5>'
```

### Get blob

```
curl --location 'http://localhost:8080/storages/containers/dump-container?blobName=dump_123456%2Foriginal%2Frequest.xml'
```

---

---

---

## File Storage




----