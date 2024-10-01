package com.reborn.server.domain.auth.domain;

import com.reborn.server.domain.auth.dao.RefreshTokenRepository;
import com.reborn.server.domain.auth.dto.Tokens;
import com.reborn.server.domain.auth.infra.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class OauthTokenGenerator {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public Tokens generate(Long memberId) {

        String subject = memberId.toString();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
        Tokens tokens = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokens.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokens;
    }
}