package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;

public class TokenMapper implements Mapper<Token, TokenDto> {

    @Override
    public TokenDto daoToDto(Token token) {
        return TokenDto.builder()
                .id(token.getId())
                .token(token.getToken())
                .build();
    }
}
