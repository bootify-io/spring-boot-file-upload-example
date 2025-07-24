package io.bootify.file_upload.profile;

import io.bootify.file_upload.file.FileDataService;
import io.bootify.file_upload.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final FileDataService fileDataService;

    public ProfileService(final ProfileRepository profileRepository,
            final FileDataService fileDataService) {
        this.profileRepository = profileRepository;
        this.fileDataService = fileDataService;
    }

    public List<ProfileDTO> findAll() {
        final List<Profile> profiles = profileRepository.findAll(Sort.by("id"));
        return profiles.stream()
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .toList();
    }

    public ProfileDTO get(final Long id) {
        return profileRepository.findById(id)
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProfileDTO profileDTO) {
        final Profile profile = new Profile();
        mapToEntity(profileDTO, profile);
        fileDataService.persistUpload(profile.getCv());
        return profileRepository.save(profile).getId();
    }

    public void update(final Long id, final ProfileDTO profileDTO) {
        final Profile profile = profileRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        fileDataService.handleUpdate(profile.getCv(), profileDTO.getCv());
        mapToEntity(profileDTO, profile);
        profileRepository.save(profile);
    }

    public void delete(final Long id) {
        final Profile profile = profileRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        fileDataService.removeFileContent(profile.getCv());
        profileRepository.delete(profile);
    }

    private ProfileDTO mapToDTO(final Profile profile, final ProfileDTO profileDTO) {
        profileDTO.setId(profile.getId());
        profileDTO.setName(profile.getName());
        profileDTO.setCv(profile.getCv());
        return profileDTO;
    }

    private Profile mapToEntity(final ProfileDTO profileDTO, final Profile profile) {
        profile.setName(profileDTO.getName());
        profile.setCv(profileDTO.getCv());
        return profile;
    }

}
