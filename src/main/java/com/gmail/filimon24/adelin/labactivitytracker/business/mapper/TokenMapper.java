package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenMapper implements ModelMapper<Token, TokenDto> {

    private final StudentMapper studentMapper;

    @Override
    public TokenDto entityToDataAccess(Token token) {
        return TokenDto.builder()
                .id(token.getId())
                .student(studentMapper.entityToDataAccess(token.getStudent()))
                .token(token.getToken())
                .build();
    }

    @Override
    public Token dataAccessToEntity(TokenDto tokenDto) {
        return Token.builder()
                .id(tokenDto.getId())
                .student(studentMapper.dataAccessToEntity(tokenDto.getStudent()))
                .token(tokenDto.getToken())
                .build();
    }
}
