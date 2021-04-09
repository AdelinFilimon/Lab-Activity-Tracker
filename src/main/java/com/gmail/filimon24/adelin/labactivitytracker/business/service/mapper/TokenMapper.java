package com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper implements Mapper<Token, TokenDto> {

    @Override
    public TokenDto daoToDto(Token token) {
        return TokenDto.builder()
                .id(token.getId())
                .token(token.getToken())
                .build();
    }

    @Override
    public Token dtoToDao(TokenDto tokenDto) {
        return Token.builder()
                .id(tokenDto.getId())
                .token(tokenDto.getToken())
                .build();
    }
}
