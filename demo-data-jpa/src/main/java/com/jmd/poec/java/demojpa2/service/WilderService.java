package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.dto.WilderDTO;
import com.jmd.poec.java.demojpa2.domain.dto.WilderFullDTO;
import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import com.jmd.poec.java.demojpa2.domain.exception.WilderException;
import com.jmd.poec.java.demojpa2.repository.WilderRepository;
import com.jmd.poec.java.demojpa2.service.mapper.WilderCustomMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WilderService {

    private final WilderRepository wilderRepository;
    private final WilderCustomMapper wilderMapper;
    private ModelMapper modelMapper;

    public WilderService(WilderRepository wilderRepository, WilderCustomMapper wilderMapper) {
        this.wilderRepository = wilderRepository;
        this.wilderMapper = wilderMapper;
        this.modelMapper = new ModelMapper();
    }


    public List<WilderFullDTO> list() {
        return wilderRepository
                .findAll()
                .stream()
                .map(wilder -> modelMapper.map(wilderMapper, WilderFullDTO.class))
                .toList();
    }

    public WilderFullDTO find(Long id) {
        Optional<Wilder> optionalWilder = wilderRepository.findById(id);

        if(optionalWilder.isEmpty()){
            throw new WilderException(HttpStatus.NOT_FOUND,"Wilder "+id+" is not found !");
        }

        return optionalWilder.map(wilder -> modelMapper.map(wilderMapper, WilderFullDTO.class)).orElse(null);
    }

    public WilderFullDTO update(Long id, WilderDTO wilder) {
        if(!wilderRepository.existsById(id)){
            throw new WilderException(HttpStatus.CONFLICT,"Wilder {} found..."+id);
        }
        Wilder savedWilder = wilderRepository.save(modelMapper.map(wilder,Wilder.class));
        return modelMapper.map(savedWilder, WilderFullDTO.class);
    }

    public WilderFullDTO create(WilderFullDTO wilder){
        if(Objects.nonNull(wilder.getId()) && wilderRepository.existsById(wilder.getId())){
            throw new WilderException(HttpStatus.CONFLICT,"Wilder {} already exists..."+wilder.getId());
        }
        Wilder savedWilder = wilderRepository.save(modelMapper.map(wilder,Wilder.class));
        return modelMapper.map(savedWilder, WilderFullDTO.class);
    }

    public void delete(Long id) {
        wilderRepository.deleteById(id);
    }

    public List<WilderFullDTO> customFind(String category) {
        return new ArrayList<>();
    }
}
