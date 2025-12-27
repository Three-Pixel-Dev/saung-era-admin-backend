package org.threepixeldev.saungeraadmin.shared.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.threepixeldev.saungeraadmin.shared.data.model.Code;
import org.threepixeldev.saungeraadmin.shared.data.model.CodeValue;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CodeJpaRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.CodeValueJpaRepository;
import org.threepixeldev.saungeraadmin.shared.dto.CodeDto;
import org.threepixeldev.saungeraadmin.shared.dto.CodeValueDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeJpaRepository codeRepository;
    private final CodeValueJpaRepository codeValueRepository;

    public List<CodeDto> getAllCodes() {
        return codeRepository.findAll().stream()
                .map(this::mapToCodeDto)
                .collect(Collectors.toList());
    }

    public CodeDto getCodeById(Long id) {
        Code code = codeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Code not found with id: " + id));
        return mapToCodeDto(code);
    }

    public CodeDto createCode(CodeDto dto) {
        Code code = new Code();
        code.setName(dto.getName());
        code.setDescription(dto.getDescription());
        Code savedCode = codeRepository.save(code);
        return mapToCodeDto(savedCode);
    }

    public CodeDto updateCode(Long id, CodeDto dto) {
        Code code = codeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Code not found with id: " + id));
        code.setName(dto.getName());
        code.setDescription(dto.getDescription());
        Code savedCode = codeRepository.save(code);
        return mapToCodeDto(savedCode);
    }

    public void deleteCode(Long id) {
        if (!codeRepository.existsById(id)) {
            throw new RuntimeException("Code not found with id: " + id);
        }
        codeRepository.deleteById(id);
    }

    public List<CodeValueDto> getValuesByCodeId(Long codeId) {
        return codeValueRepository.findByCode_Id(codeId).stream()
                .map(this::mapToCodeValueDto)
                .collect(Collectors.toList());
    }

    public CodeValueDto createCodeValue(CodeValueDto dto) {
        Code code = codeRepository.findById(dto.getCodeId())
                .orElseThrow(() -> new RuntimeException("Code not found with id: " + dto.getCodeId()));

        CodeValue codeValue = new CodeValue();
        codeValue.setName(dto.getName());
        codeValue.setDescription(dto.getDescription());
        codeValue.setCode(code);

        CodeValue savedValue = codeValueRepository.save(codeValue);
        return mapToCodeValueDto(savedValue);
    }

    public CodeValueDto updateCodeValue(Long id, CodeValueDto dto) {
        CodeValue codeValue = codeValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CodeValue not found with id: " + id));

        codeValue.setName(dto.getName());
        codeValue.setDescription(dto.getDescription());
        
        if (dto.getCodeId() != null && !dto.getCodeId().equals(codeValue.getCode().getId())) {
             Code newCode = codeRepository.findById(dto.getCodeId())
                .orElseThrow(() -> new RuntimeException("Code not found with id: " + dto.getCodeId()));
             codeValue.setCode(newCode);
        }

        CodeValue savedValue = codeValueRepository.save(codeValue);
        return mapToCodeValueDto(savedValue);
    }

    public void deleteCodeValue(Long id) {
        if (!codeValueRepository.existsById(id)) {
            throw new RuntimeException("CodeValue not found with id: " + id);
        }
        codeValueRepository.deleteById(id);
    }

    private CodeDto mapToCodeDto(Code entity) {
        CodeDto dto = new CodeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private CodeValueDto mapToCodeValueDto(CodeValue entity) {
        CodeValueDto dto = new CodeValueDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCodeId(entity.getCode().getId());
        return dto;
    }
}
