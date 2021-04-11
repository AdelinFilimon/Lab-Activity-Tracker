package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TokenNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.TokenMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    public TokenDto createToken() {
        String generatedString = RandomString.make(CustomApplicationProperties.tokenFieldLen);
        Token token = Token.builder().token(generatedString).build();
        token = tokenRepository.save(token);
        return tokenMapper.daoToDto(token);
    }

    public List<TokenDto> getTokens() {
         return tokenRepository.findAll()
                .stream()
                .map(tokenMapper::daoToDto)
                .collect(Collectors.toList());
    }

    public TokenDto getToken(Long id) {
        Token token =  tokenRepository.findById(id)
                        .orElseThrow(() -> new TokenNotFoundException(id));
        return tokenMapper.daoToDto(token);
    }

    public void deleteToken(Long id) {
        if (!tokenRepository.existsById(id)) throw new TokenNotFoundException(id);
        tokenRepository.deleteById(id);
    }

    @Transactional
    public void deleteToken(String token) {
        tokenRepository.removeTokenByToken(token);
    }

    public void deleteTokens() {
        tokenRepository.deleteAll();
    }

}
