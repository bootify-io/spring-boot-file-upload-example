package io.bootify.file_upload.profile;

import io.bootify.file_upload.file.FileData;
import io.bootify.file_upload.file.FileDataService;
import io.bootify.file_upload.util.JsonStringFormatter;
import io.bootify.file_upload.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tools.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ObjectMapper objectMapper;
    private final FileDataService fileDataService;

    public ProfileController(final ProfileService profileService, final ObjectMapper objectMapper,
            final FileDataService fileDataService) {
        this.profileService = profileService;
        this.objectMapper = objectMapper;
        this.fileDataService = fileDataService;
    }

    @InitBinder
    public void jsonFormatting(final WebDataBinder binder) {
        binder.addCustomFormatter(new JsonStringFormatter<FileData>(objectMapper) {
        }, "cv");
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("profiles", profileService.findAll());
        return "profile/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("profile") final ProfileDTO profileDTO) {
        return "profile/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("profile") @Valid final ProfileDTO profileDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "profile/add";
        }
        profileService.create(profileDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("profile.create.success"));
        return "redirect:/profiles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("profile", profileService.get(id));
        model.addAttribute("withDownloads", true);
        return "profile/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("profile") @Valid final ProfileDTO profileDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "profile/edit";
        }
        profileService.update(id, profileDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("profile.update.success"));
        return "redirect:/profiles";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        profileService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("profile.delete.success"));
        return "redirect:/profiles";
    }

    @GetMapping("/{id}/cv/{filename}")
    public ResponseEntity<InputStreamResource> downloadCv(
            @PathVariable(name = "id") final Long id) {
        final ProfileDTO profileDTO = profileService.get(id);
        return fileDataService.provideDownload(profileDTO.getCv());
    }

}
