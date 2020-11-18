package br.com.itau.fatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Bootcamp01TemplateFaturaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bootcamp01TemplateFaturaApplication.class, args);
    }

}
