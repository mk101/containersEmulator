package com.example.auntificationservice.security;

import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class KeyUtils {

    @Value("${jwt.keys.access.private}")
    private Resource accessPrivateKey;
    @Value("${jwt.keys.access.public}")
    private Resource accessPublicKey;

    @Value("${jwt.keys.refresh.private}")
    private Resource refreshPrivateKey;
    @Value("${jwt.keys.refresh.public}")
    private Resource refreshPublicKey;

    private KeyPair accessTokenKeyPair;
    private KeyPair refreshTokenKeyPair;

    public RSAPublicKey getAccessTokenPublicKey() {
        return (RSAPublicKey) getAccessTokenKeyPair().getPublic();
    }

    public RSAPrivateKey getAccessTokenPrivateKey() {
        return (RSAPrivateKey) getAccessTokenKeyPair().getPrivate();
    }

    public RSAPublicKey getRefreshTokenPublicKey() {
        return (RSAPublicKey) getRefreshTokenKeyPair().getPublic();
    }

    public RSAPrivateKey getRefreshTokenPrivateKey() {
        return (RSAPrivateKey) getRefreshTokenKeyPair().getPrivate();
    }

    private KeyPair getAccessTokenKeyPair() {
        if (accessTokenKeyPair == null) {
            accessTokenKeyPair = getKeyPair(accessPublicKey, accessPrivateKey);
        }

        return accessTokenKeyPair;
    }

    private KeyPair getRefreshTokenKeyPair() {
        if (refreshTokenKeyPair == null) {
            refreshTokenKeyPair = getKeyPair(refreshPublicKey, refreshPrivateKey);
        }

        return refreshTokenKeyPair;
    }

    private KeyPair getKeyPair(Resource publicKeyResource, Resource privateKeyResource) {

        if (publicKeyResource.exists() && privateKeyResource.exists()) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                byte[] publicKeyBytes = Files.readAllBytes(publicKeyResource.getFile().toPath());
                EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
                PublicKey publicKey = keyFactory.generatePublic(publicSpec);

                byte[] privateKeyBytes = Files.readAllBytes(privateKeyResource.getFile().toPath());
                PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);

                return new KeyPair(publicKey, privateKey);
            } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }

        throw new NullArgumentException("Files with keys don't exist");
    }
}
