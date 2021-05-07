package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TokenNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TopicNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.finder.EntityFromDtoFinder;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.LaboratoryClassMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.TopicMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TopicRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService implements BasicService<TopicDto, Long>, UpdateEntityService<TopicDto, Long> {

    private final TopicRepository topicRepository;
    private final EntityFromDtoFinder entityFinder;
    private final TopicMapper topicMapper;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public Object create(Object topic) {
        TopicDto topicDto = (TopicDto) topic;

        LaboratoryClass laboratoryClass = entityFinder.findLaboratoryClass(topicDto.getLaboratoryClass());
        topicDto.setLaboratoryClass(laboratoryClassMapper.entityToDataAccess(laboratoryClass));
        Topic topicDao = topicRepository.save(topicMapper.dataAccessToEntity(topicDto));
        return topicMapper.entityToDataAccess(topicDao);
    }

    @Override
    public TopicDto get(Long id) {
        Topic topic =  topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
        return topicMapper.entityToDataAccess(topic);
    }

    @Override
    public List<TopicDto> getAll() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, TopicDto topicDto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TokenNotFoundException(id));

        LaboratoryClassDto laboratoryClassDto = topicDto.getLaboratoryClass();

        if (laboratoryClassDto != null) {
            topic.setLaboratoryClass(entityFinder.findLaboratoryClass(laboratoryClassDto));
        }

        if (topicDto.getTitle() != null)
            topic.setTitle(topicDto.getTitle());

        topicRepository.save(topic);
    }

    @Override
    public void deleteAll() {
        topicRepository.deleteAll();
    }

    @Override
    public void delete(Long id) {
        if (!topicRepository.existsById(id))
            throw new TopicNotFoundException(id);
        topicRepository.deleteById(id);
    }

}
