package com.kentoes.jwtAuth.securities;

import com.kentoes.jwtAuth.models.entities.errLog.ErrLog;
import com.kentoes.jwtAuth.services.errLog.ErrLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

@Slf4j
@Service
public class KeyGenerator {
    @Autowired
    private ErrLogService errLogService;

    @Value("${kentoes.security.jwt.keystore-location}")
    private String keyStorePath;

    @Value("${kentoes.security.jwt.keystore-password}")
    private String keyStorePassword;

    @Value("${kentoes.security.jwt.key-alias}")
    private String keyAlias;

    @Value("${kentoes.security.jwt.private-key-passphrase}")
    private String privateKeyPassphrase;

    protected String pkg = this.getClass().getPackageName();
    protected String cls = this.getClass().getSimpleName();
    protected String clsPth = this.pkg + "." + this.cls;
    private Exception exception = null;
    private ErrLog errLog = null;

    private KeyStore keyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
            keyStore.load(stream, keyStorePassword.toCharArray());
            return keyStore;
        } catch (KeyStoreException e) {
            exception = e;
            errLog = new ErrLog("System", "KeyStoreException", e.getMessage(), clsPth, "keyStore");
        } catch (CertificateException e) {
            exception = e;
            errLog = new ErrLog("System", "CertificateException", e.getMessage(), clsPth, "keyStore");
        } catch (IOException e) {
            exception = e;
            errLog = new ErrLog("System", "IOException", e.getMessage(), clsPth, "keyStore");
        } catch (NoSuchAlgorithmException e) {
            exception = e;
            errLog = new ErrLog("System", "NoSuchAlgorithmException", e.getMessage(), clsPth, "keyStore");
        }
        errLogService.save(errLog);
        throw new RuntimeException(exception);
    }

    public PrivateKey jwtSigningKey() {
        try {
            Key key = keyStore().getKey(keyAlias, privateKeyPassphrase.toCharArray());
            if (key instanceof PrivateKey) return (PrivateKey) key;
        } catch (UnrecoverableKeyException e) {
            exception = e;
            errLog = new ErrLog("System", "UnrecoverableKeyException", e.getMessage(), clsPth, "jwtSigningKey");
        } catch (KeyStoreException e) {
            exception = e;
            errLog = new ErrLog("System", "KeyStoreException", e.getMessage(), clsPth, "jwtSigningKey");
        } catch (NoSuchAlgorithmException e) {
            exception = e;
            errLog = new ErrLog("System", "NoSuchAlgorithmException", e.getMessage(), clsPth, "jwtSigningKey");
        }
        if (errLog != null)
            errLogService.save(errLog);
        throw new RuntimeException(exception);
    }

    public PublicKey jwtValidationKey() {
        try {
            Certificate certificate = keyStore().getCertificate(keyAlias);
            PublicKey publicKey = certificate.getPublicKey();

            if (publicKey != null) return publicKey;
        } catch (KeyStoreException e) {
            exception = e;
            errLog = new ErrLog("System", "NoSuchAlgorithmException", e.getMessage(), clsPth, "jwtValidationKey");
        }

        errLogService.save(errLog);
        throw new RuntimeException(exception);
    }
}
