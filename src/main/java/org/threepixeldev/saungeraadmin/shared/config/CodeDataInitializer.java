package org.threepixeldev.saungeraadmin.shared.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.threepixeldev.saungeraadmin.shared.data.model.Code;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CodeJpaRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CodeDataInitializer implements CommandLineRunner {

    private final CodeJpaRepository codeRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeCodes();
    }

    private void initializeCodes() {
        // Initialize Color code
        if (!codeRepository.findByName("Color").isPresent()) {
            Code colorCode = new Code();
            colorCode.setName("Color");
            colorCode.setDescription("Product color variations and options. Used to categorize and filter products by their available colors such as Red, Blue, Green, Black, White, and other color variants.");
            colorCode.setCreatedBy(1L);
            colorCode.setUpdatedBy(1L);
            codeRepository.save(colorCode);
            log.info("Initialized Color code");
        } else {
            log.debug("Color code already exists, skipping initialization");
        }

        // Initialize Size code
        if (!codeRepository.findByName("Size").isPresent()) {
            Code sizeCode = new Code();
            sizeCode.setName("Size");
            sizeCode.setDescription("Product size options and measurements. Used to categorize products by their available sizes such as Small (S), Medium (M), Large (L), Extra Large (XL), or specific measurements for items like shoes, clothing, and other dimension-based products.");
            sizeCode.setCreatedBy(1L);
            sizeCode.setUpdatedBy(1L);
            codeRepository.save(sizeCode);
            log.info("Initialized Size code");
        } else {
            log.debug("Size code already exists, skipping initialization");
        }
    }
}

