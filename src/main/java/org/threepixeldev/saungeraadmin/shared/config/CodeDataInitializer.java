package org.threepixeldev.saungeraadmin.shared.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.threepixeldev.saungeraadmin.shared.data.model.Code;
import org.threepixeldev.saungeraadmin.shared.data.model.CodeValue;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CodeJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CodeValueJpaRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CodeDataInitializer implements CommandLineRunner {

    private final CodeJpaRepository codeRepository;
    private final CodeValueJpaRepository codeValueRepository;

    @Override
    public void run(String... args) throws Exception {
        Code colorCode = initializeColorCode();
        Code sizeCode = initializeSizeCode();
        
        initializeColorValues(colorCode);
        initializeSizeValues(sizeCode);
    }

    private Code initializeColorCode() {
        Code colorCode = codeRepository.findByName("Color").orElse(null);
        if (colorCode == null) {
            colorCode = new Code();
            colorCode.setName("Color");
            colorCode.setDescription("Product color variations and options. Used to categorize and filter products by their available colors such as Red, Blue, Green, Black, White, and other color variants.");
            colorCode.setCreatedBy(1L);
            colorCode.setUpdatedBy(1L);
            colorCode = codeRepository.save(colorCode);
            log.info("Initialized Color code");
        } else {
            log.debug("Color code already exists, skipping initialization");
        }
        return colorCode;
    }

    private Code initializeSizeCode() {
        Code sizeCode = codeRepository.findByName("Size").orElse(null);
        if (sizeCode == null) {
            sizeCode = new Code();
            sizeCode.setName("Size");
            sizeCode.setDescription("Product size options and measurements. Used to categorize products by their available sizes such as Small (S), Medium (M), Large (L), Extra Large (XL), or specific measurements for items like shoes, clothing, and other dimension-based products.");
            sizeCode.setCreatedBy(1L);
            sizeCode.setUpdatedBy(1L);
            sizeCode = codeRepository.save(sizeCode);
            log.info("Initialized Size code");
        } else {
            log.debug("Size code already exists, skipping initialization");
        }
        return sizeCode;
    }

    private void initializeColorValues(Code colorCode) {
        List<CodeValue> existingValues = codeValueRepository.findByCode(colorCode);
        
        // Blue
        if (existingValues.stream().noneMatch(cv -> "Blue".equals(cv.getName()))) {
            CodeValue blue = new CodeValue();
            blue.setCode(colorCode);
            blue.setName("Blue");
            blue.setDescription("#0000FF");
            blue.setCreatedBy(1L);
            blue.setUpdatedBy(1L);
            codeValueRepository.save(blue);
            log.info("Initialized Color value: Blue");
        }

        // Red
        if (existingValues.stream().noneMatch(cv -> "Red".equals(cv.getName()))) {
            CodeValue red = new CodeValue();
            red.setCode(colorCode);
            red.setName("Red");
            red.setDescription("#FF0000");
            red.setCreatedBy(1L);
            red.setUpdatedBy(1L);
            codeValueRepository.save(red);
            log.info("Initialized Color value: Red");
        }

        // Green
        if (existingValues.stream().noneMatch(cv -> "Green".equals(cv.getName()))) {
            CodeValue green = new CodeValue();
            green.setCode(colorCode);
            green.setName("Green");
            green.setDescription("#00FF00");
            green.setCreatedBy(1L);
            green.setUpdatedBy(1L);
            codeValueRepository.save(green);
            log.info("Initialized Color value: Green");
        }

        // Black
        if (existingValues.stream().noneMatch(cv -> "Black".equals(cv.getName()))) {
            CodeValue black = new CodeValue();
            black.setCode(colorCode);
            black.setName("Black");
            black.setDescription("#000000");
            black.setCreatedBy(1L);
            black.setUpdatedBy(1L);
            codeValueRepository.save(black);
            log.info("Initialized Color value: Black");
        }

        // White
        if (existingValues.stream().noneMatch(cv -> "White".equals(cv.getName()))) {
            CodeValue white = new CodeValue();
            white.setCode(colorCode);
            white.setName("White");
            white.setDescription("#FFFFFF");
            white.setCreatedBy(1L);
            white.setUpdatedBy(1L);
            codeValueRepository.save(white);
            log.info("Initialized Color value: White");
        }
    }

    private void initializeSizeValues(Code sizeCode) {
        List<CodeValue> existingValues = codeValueRepository.findByCode(sizeCode);
        
        // S - Small
        if (existingValues.stream().noneMatch(cv -> "S".equals(cv.getName()))) {
            CodeValue small = new CodeValue();
            small.setCode(sizeCode);
            small.setName("S");
            small.setDescription("Small");
            small.setCreatedBy(1L);
            small.setUpdatedBy(1L);
            codeValueRepository.save(small);
            log.info("Initialized Size value: S");
        }

        // M - Medium
        if (existingValues.stream().noneMatch(cv -> "M".equals(cv.getName()))) {
            CodeValue medium = new CodeValue();
            medium.setCode(sizeCode);
            medium.setName("M");
            medium.setDescription("Medium");
            medium.setCreatedBy(1L);
            medium.setUpdatedBy(1L);
            codeValueRepository.save(medium);
            log.info("Initialized Size value: M");
        }

        // L - Large
        if (existingValues.stream().noneMatch(cv -> "L".equals(cv.getName()))) {
            CodeValue large = new CodeValue();
            large.setCode(sizeCode);
            large.setName("L");
            large.setDescription("Large");
            large.setCreatedBy(1L);
            large.setUpdatedBy(1L);
            codeValueRepository.save(large);
            log.info("Initialized Size value: L");
        }

        // XL - Extra Large
        if (existingValues.stream().noneMatch(cv -> "XL".equals(cv.getName()))) {
            CodeValue xl = new CodeValue();
            xl.setCode(sizeCode);
            xl.setName("XL");
            xl.setDescription("Extra Large");
            xl.setCreatedBy(1L);
            xl.setUpdatedBy(1L);
            codeValueRepository.save(xl);
            log.info("Initialized Size value: XL");
        }

        // XXL - Extra Extra Large
        if (existingValues.stream().noneMatch(cv -> "XXL".equals(cv.getName()))) {
            CodeValue xxl = new CodeValue();
            xxl.setCode(sizeCode);
            xxl.setName("XXL");
            xxl.setDescription("Extra Extra Large");
            xxl.setCreatedBy(1L);
            xxl.setUpdatedBy(1L);
            codeValueRepository.save(xxl);
            log.info("Initialized Size value: XXL");
        }
    }
}
