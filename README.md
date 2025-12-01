# Spring Boot File Upload Example

This is an example project for a file upload in Spring Boot `4.0.0`. Uploads can be part of any form using DTOs. In this example you can manage profiles with a resume in PDF or DOC format in a Thymeleaf frontend. Implementation details:

* Separate table `FileContent` for storing file content
* The entity `Profile` contains a JSON object `FileData` with the name and UID of the file
* The `FileDataService` provides utility functions for uploads and downloads
* The `ConverterConfig` registers a converter for transforming uploads from `MultipartFile` to a `FileData` object transparently
* The annotation `ValidFileType` ensures the correct file type

![File Upload Example](https://s3-eu-central-1.amazonaws.com/bootify-prod/ext/img/templates/fileUploadExample.png)

This project was created using [Bootify.io](https://bootify.io). Choose your preferred frontend and preferences, **create your own database schema including file fields, and get a working Spring Boot application** directly in your browser.

## Development

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override settings for development.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/file-upload-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=io.bootify/file-upload
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
* [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)  
* [Bootstrap docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)  
