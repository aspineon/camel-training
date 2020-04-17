package pl.training.camel.modulefour;

import org.apache.camel.CamelContext;
import org.apache.camel.component.crypto.DigitalSignatureEndpoint;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.*;

@Configuration
public class Config {

    @Bean
    public KeyPair securityKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    @Bean
    public PrivateKey securityPrivateKey(KeyPair securityKeyPair) {
        return securityKeyPair.getPrivate();
    }

    @Bean
    public PublicKey securityPublicKey(KeyPair securityKeyPair) {
        return securityKeyPair.getPublic();
    }

    @Bean
    public CamelContextConfiguration contextConfiguration(PrivateKey securityPrivateKey, PublicKey securityPublicKey) {
        return new CamelContextConfiguration() {

            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                camelContext.getEndpoint("crypto:sign://rsa?algorithm=MD5withRSA", DigitalSignatureEndpoint.class).setPrivateKey(securityPrivateKey);
                camelContext.getEndpoint("crypto:verify://rsa?algorithm=MD5withRSA", DigitalSignatureEndpoint.class).setPublicKey(securityPublicKey);
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }

    @Bean
    public Key secretKey() throws GeneralSecurityException, IOException {
        KeyStoreParameters parameters = new KeyStoreParameters();
        parameters.setPassword("megasecret");
        parameters.setResource("./module-four/secrets.jceks");
        parameters.setType("JCEKS");

        KeyStore keyStore = parameters.createKeyStore();
        return keyStore.getKey("training", "secret".toCharArray());
    }

}
