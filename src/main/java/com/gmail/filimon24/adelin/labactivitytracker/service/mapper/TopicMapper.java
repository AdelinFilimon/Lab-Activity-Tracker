package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Topic;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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

}
