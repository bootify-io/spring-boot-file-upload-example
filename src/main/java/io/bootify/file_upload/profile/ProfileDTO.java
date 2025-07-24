package io.bootify.file_upload.profile;

import io.bootify.file_upload.file.FileData;
import io.bootify.file_upload.file.ValidFileType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ProfileDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Valid
    @ValidFileType({"pdf", "doc"})
    private FileData cv;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public FileData getCv() {
        return cv;
    }

    public void setCv(final FileData cv) {
        this.cv = cv;
    }

}
