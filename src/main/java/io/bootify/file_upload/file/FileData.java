package io.bootify.file_upload.file;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;


public class FileData {

    @NotNull
    @Size(max = 255)
    private String uid;

    @NotNull
    @Size(max = 255)
    private String fileName;

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final FileData fileData = ((FileData)other);
        return Objects.equals(uid, fileData.uid) &&
                Objects.equals(fileName, fileData.fileName);
    }

}
