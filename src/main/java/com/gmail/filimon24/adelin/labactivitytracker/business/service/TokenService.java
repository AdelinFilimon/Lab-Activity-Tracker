package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TokenNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.TokenMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService implements BasicService<TokenDto, Long> {

    private final TokenRepository tokenRepository;
    private final StudentMapper studentMapper;
    private final TokenMapper tokenMapper;

    @Override
    public Object create(Object student) {
        StudentDto studentDto = (StudentDto) student;
        String generatedString = RandomString.make(CustomApplicationProperties.TOKEN_FIELD_LEN);
        Token token = Token.builder()
                .token(generatedString)
                .student(studentMapper.dataAccessToEntity(studentDto))
                .build();
        token = tokenRepository.save(token);
        return tokenMapper.entityToDataAccess(token);
    }

    @Override
    public List<TokenDto> getAll() {
         return tokenRepository.findAll()
                .stream()
                .map(tokenMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public TokenDto get(Long id) {
        Token token =  tokenRepository.findById(id)
                        .orElseThrow(() -> new TokenNotFoundException(id));
        return tokenMapper.entityToDataAccess(token);
    }

    @Override
    public void delete(Long id) {
        if (!tokenRepository.existsById(id)) throw new TokenNotFoundException(id);
        tokenRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tokenRepository.deleteAll();
    }

    public TokenDto get(String token) {
        Token tokenObj = tokenRepository.findTokenByToken(token);
        if (tokenObj == null) throw new TokenNotFoundException(token);
        return tokenMapper.entityToDataAccess(tokenObj);
    }

    public void delete(String token) {
        tokenRepository.removeTokenByToken(token);
    }
}
