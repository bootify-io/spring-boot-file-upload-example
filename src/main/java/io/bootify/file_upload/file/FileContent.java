package io.bootify.file_upload.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.sql.Blob;


@Entity
public class FileContent {

    @Id
    @Column(nullable = false, updatable = false)
    private String uid;

    @Column(nullable = false)
    @Lob
    private Blob content;

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public Blob getContent() {
        return content;
    }

    public void setContent(final Blob content) {
        this.content = content;
    }

}
