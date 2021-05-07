package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicMapper implements ModelMapper<Topic, TopicDto> {

    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public TopicDto entityToDataAccess(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .laboratoryClass(laboratoryClassMapper.entityToDataAccess(topic.getLaboratoryClass()))
                .build();
    }

    @Override
    public Topic dataAccessToEntity(TopicDto topicDto) {
        return Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .laboratoryClass(laboratoryClassMapper.dataAccessToEntity(topicDto.getLaboratoryClass()))
                .build();
    }
}
