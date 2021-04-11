package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator implements Validator<String>{

    private final TokenRepository tokenRepository;

    @Override
    public Boolean isValid(String token) {
        if (token.length() != CustomApplicationProperties.tokenFieldLen) return false;
        return tokenRepository.existsTokenByToken(token);
    }
}
