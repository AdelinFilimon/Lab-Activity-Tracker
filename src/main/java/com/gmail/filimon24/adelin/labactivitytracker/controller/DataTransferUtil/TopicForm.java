package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicForm {
    private Long id;
    private String title;
    private Integer labNumber;
    private Long labId;
    private String labTitle;

    public TopicDto toTopicDto() {
        LaboratoryClassDto laboratoryClassDto = LaboratoryClassDto.builder()
                .id(labId)
                .labNumber(labNumber)
                .title(labTitle)
                .build();

        return TopicDto.builder()
                .title(title)
                .laboratoryClass(laboratoryClassDto)
                .build();
    }

    public static TopicForm fromTopicDto(TopicDto topicDto) {
        return TopicForm.builder()
                .id(topicDto.getId())
                .title(topicDto.getTitle())
                .labId(topicDto.getLaboratoryClass().getId())
                .labNumber(topicDto.getLaboratoryClass().getLabNumber())
                .labTitle(topicDto.getLaboratoryClass().getTitle())
                .build();
    }
}
