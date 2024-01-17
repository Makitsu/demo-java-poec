package com.jmd.poec.java.demojpa2.service.mapper;

import com.jmd.poec.java.demojpa2.domain.dto.WilderDTO;
import com.jmd.poec.java.demojpa2.domain.entity.Skill;
import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import com.jmd.poec.java.demojpa2.domain.entity.WilderInformation;
import com.jmd.poec.java.demojpa2.repository.SkillRepository;
import com.jmd.poec.java.demojpa2.repository.WilderInformationRepository;
import com.jmd.poec.java.demojpa2.repository.WilderRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class WilderCustomMapper {

    private final WilderInformationRepository wilderInformationRepository;
    private final WilderRepository wilderRepository;
    private final SkillRepository skillRepository;

    public WilderCustomMapper(WilderInformationRepository wilderInformationRepository, WilderRepository wilderRepository, SkillRepository skillRepository) {
        this.wilderInformationRepository = wilderInformationRepository;
        this.wilderRepository = wilderRepository;
        this.skillRepository = skillRepository;
    }


    public WilderDTO toDto(Wilder wilder){
        WilderDTO dto = new WilderDTO();
        dto.setId(wilder.getId());
        dto.setCategory(wilder.getCategory());
        dto.setEmail(wilder.getEmail());
        dto.setName(wilder.getName());

        if(Objects.nonNull(wilder.getWilderInformation())){
            dto.setInformationContent(wilder.getWilderInformation().getContent());
        }

        //if(CollectionUtils.isEmpty(wilder.getSkills()))
        if(Objects.nonNull(wilder.getSkills()) && !wilder.getSkills().isEmpty()){
            Set<String> skillTitles = new HashSet<>();
            for (Skill skill : wilder.getSkills()) {
                skillTitles.add(skill.getTitle());
            }
            dto.setSkillTitles(skillTitles);
        }

        return dto;
    }

    public Wilder toEntity(WilderDTO dto){
        Wilder entity = new Wilder();
        entity.setId(dto.getId());
        entity.setCategory(dto.getCategory());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        enrichWithWilderInformation(dto, entity);

        enrichWithSkills(dto, entity);

        return entity;
    }

    private void enrichWithSkills(WilderDTO dto, Wilder entity) {
        if(!CollectionUtils.isEmpty(dto.getSkillTitles())){
            entity.setSkills(new ArrayList<>());

            if(Objects.isNull(entity.getId())){
                for (String skillTitle : dto.getSkillTitles()) {
                    Skill skill = new Skill();
                    skill.setTitle(skillTitle);
                    skill.setWilder(List.of(entity));
                    entity.getSkills().add(skill);
                }
                return;
            }

            Optional<Wilder> existingWilder = wilderRepository.findById(entity.getId());

            if(existingWilder.isPresent()){
                for (String skillTitle : dto.getSkillTitles()) {
                    if(!CollectionUtils.isEmpty(existingWilder.get().getSkills())){
                        Optional<Skill> foundSkill = existingWilder.get().getSkills()
                                .stream()
                                .filter(skill -> Objects.equals(skill.getTitle(), dto.getSkillTitles()))
                                .findAny();
                        if(foundSkill.isPresent()){
                            entity.getSkills().add(foundSkill.get());
                        }else{
                            Skill skill = new Skill();
                            skill.setTitle(skillTitle);
                            skill.setWilder(List.of(entity));
                            entity.getSkills().add(skill);
                        }
                    }else{
                        Skill skill = new Skill();
                        skill.setTitle(skillTitle);
                        skill.setWilder(List.of(entity));
                        entity.getSkills().add(skill);
                    }
                }
            }else{
                for (String skillTitle : dto.getSkillTitles()) {
                    Skill skill = new Skill();
                    skill.setTitle(skillTitle);
                    skill.setWilder(List.of(entity));
                    entity.getSkills().add(skill);
                }
            }

        }
    }

    private void enrichWithWilderInformation(WilderDTO dto, Wilder entity) {
        if(Strings.isNotBlank(dto.getInformationContent())) {
            if(Objects.nonNull(entity.getId()) && wilderRepository.existsById(entity.getId())){
                Optional<WilderInformation> optionalWilderInformation = wilderInformationRepository
                        .findFirstByContentAndWilder_Id(dto.getInformationContent(),entity.getId());
                if(optionalWilderInformation.isPresent()){
                    entity.setWilderInformation(optionalWilderInformation.get());
                }else{
                    WilderInformation wilderInformation = new WilderInformation();
                    wilderInformation.setWilder(entity);
                    wilderInformation.setContent(dto.getInformationContent());
                    entity.setWilderInformation(wilderInformation);
                }
            }else{
                WilderInformation wilderInformation = new WilderInformation();
                wilderInformation.setWilder(entity);
                wilderInformation.setContent(dto.getInformationContent());
                entity.setWilderInformation(wilderInformation);
            }
        }
    }

}
