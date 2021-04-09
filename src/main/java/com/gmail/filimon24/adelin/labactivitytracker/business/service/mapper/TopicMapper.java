package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicMapper implements Mapper<Topic, TopicDto> {

    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public TopicDto daoToDto(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .laboratoryClass(laboratoryClassMapper.daoToDto(topic.getLaboratoryClass()))
                .build();
    }

    @Override
    public Topic dtoToDao(TopicDto topicDto) {
        return Topic.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .laboratoryClass(laboratoryClassMapper.dtoToDao(topicDto.getLaboratoryClass()))
                .build();
    }
}
