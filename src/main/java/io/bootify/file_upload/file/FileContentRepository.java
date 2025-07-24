package io.bootify.file_upload.file;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FileContentRepository extends JpaRepository<FileContent, String> {
}
