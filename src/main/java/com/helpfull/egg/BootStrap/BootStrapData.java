package com.helpfull.egg.BootStrap;

import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.repositories.ZonaRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private final ZonaRepository zonaRepository;

    public BootStrapData(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (zonaRepository.count() > 0) {
            return;
        }
        crearZonasDefault();

        System.out.println("numero de zonas:" + zonaRepository.count());
    }
//https://mkyong.com/java/java-read-a-file-from-resources-folder/

    private void crearZonasDefault() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("localidad-provincia.csv");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                Zona zona = new Zona(
                        capitalizarPalabra(line.split(";")[0]),
                        capitalizarPalabra(line.split(";")[1]));
                zonaRepository.save(zona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String capitalizarPalabra(String string) {
        String resultado = "";
        for (String p : string.split(" ")) {
            if(p.length() > 1)
                resultado += " " + p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase();
            else
                resultado += " " + p.toUpperCase();
        }
        return resultado.trim();
    }

}
