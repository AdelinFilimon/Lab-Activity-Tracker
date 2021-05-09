package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator implements Validator<TokenDto>{

    private final TokenRepository tokenRepository;

    @Override
    public Boolean isValid(TokenDto tokenDto) {
        String token = tokenDto.getToken();
        if (token.length() != CustomApplicationProperties.TOKEN_FIELD_LEN) return false;
        return tokenRepository.existsTokenByToken(token);
    }
}
