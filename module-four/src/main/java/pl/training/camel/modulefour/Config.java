package pl.training.camel.modulefour;

import org.apache.camel.CamelContext;
import org.apache.camel.component.crypto.DigitalSignatureEndpoint;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /*@Bean
    public KeyStoreParameters trainingKeystore() {
        KeyStoreParameters parameters = new KeyStoreParameters();
        parameters.setPassword("secret");
        parameters.setResource("./module-four/training.jks");
        return parameters;
    }

    @Bean
    public KeyStoreParameters trainingTruststore() {
        KeyStoreParameters parameters = new KeyStoreParameters();
        parameters.setPassword("secret");
        parameters.setResource("./module-four/truststore.jks");
        return parameters;
    }*/

}
