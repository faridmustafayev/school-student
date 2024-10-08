package com.example.spring.school.student.cache;

import com.example.spring.school.student.jwt.AccessTokenClaimSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AuthCacheData implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;

    private AccessTokenClaimSet accessTokenClaimSet;

    private String publicKey;
}
