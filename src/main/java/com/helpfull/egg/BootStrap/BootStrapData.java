package com.helpfull.egg.BootStrap;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.FamiliarAcargo;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.InteresVoluntario;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.enums.Rol;
import com.helpfull.egg.repositories.AmigoRepository;
import com.helpfull.egg.repositories.FamiliarRepository;
import com.helpfull.egg.repositories.VoluntarioRepository;
import com.helpfull.egg.repositories.ZonaRepository;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private final ZonaRepository zonaRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final AmigoRepository amigoRepository;
    private final FamiliarRepository familiarRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BootStrapData(ZonaRepository zonaRepository, VoluntarioRepository voluntarioRepository, AmigoRepository amigoRepository, FamiliarRepository familiarRepository) {
        this.zonaRepository = zonaRepository;
        this.voluntarioRepository = voluntarioRepository;
        this.amigoRepository = amigoRepository;
        this.familiarRepository = familiarRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (zonaRepository.count() == 0) {
            crearZonasDefault();
        }

        if (voluntarioRepository.count() == 0) {
            crearVolundariados();
            crearAdministradores();
        }
        if (familiarRepository.count() == 0) {
            crearFamiliaresDefault();
        }
        if (amigoRepository.count() == 0) {
            crearAmigosDefault();
        }

        System.out.println("numero de zonas:" + zonaRepository.count());
    }

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

    private void crearVolundariados() throws ParseException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("voluntarios.csv");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String mimeType = "";
                Voluntario voluntario;
                voluntario = new Voluntario(
                        capitalizarPalabra(line.split(",")[0]),
                        passwordEncoder.encode(line.split(",")[1]),
                        capitalizarPalabra(line.split(",")[2]),
                        capitalizarPalabra(line.split(",")[3]),
                        capitalizarPalabra(line.split(",")[4]),
                        stringToInt(line.split(",")[5]),
                        capitalizarPalabra(line.split(",")[6]),
                        stringToLong(line.split(",")[7]),
                        stringToLocalDate(line.split(",")[8]),
                        capitalizarPalabra(line.split(",")[9]),
                        leerContenidoFoto(line.split(",")[10], mimeType),
                        Rol.ROLE_VOLUNTARIO,
                        LocalDate.now(),
                        LocalDate.now(),
                        stringToCollectionInteresVoluntario(line.split(",")[11]),
                        null, //Amigos
                        zonaRepository.buscarPorProvincia(line.split(",")[12]).get(0));

                voluntarioRepository.save(voluntario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearAdministradores() throws ParseException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("administrador.csv");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String mimeType = "";
                Voluntario administrador = new Voluntario(
                        capitalizarPalabra(line.split(",")[0]),
                        passwordEncoder.encode(line.split(",")[1]),
                        capitalizarPalabra(line.split(",")[2]),
                        capitalizarPalabra(line.split(",")[3]),
                        capitalizarPalabra(line.split(",")[4]),
                        stringToInt(line.split(",")[5]),
                        capitalizarPalabra(line.split(",")[6]),
                        stringToLong(line.split(",")[7]),
                        stringToLocalDate(line.split(",")[8]),
                        capitalizarPalabra(line.split(",")[9]),
                        leerContenidoFoto(line.split(",")[10], mimeType),
                        Rol.ROLE_ADMIN,
                        LocalDate.now(),
                        LocalDate.now(),
                        stringToCollectionInteresVoluntario(line.split(",")[11]),
                        null, //Amigos
                        zonaRepository.buscarPorProvincia(line.split(",")[12]).get(0));
                voluntarioRepository.save(administrador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearAmigosDefault() throws ParseException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("amigos.csv");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String mimeType = "";
                    Amigo amigo = new Amigo(
                            capitalizarPalabra(line.split(",")[0]),
                            capitalizarPalabra(line.split(",")[1]),
                            stringToLocalDate(line.split(",")[2]),
                            capitalizarPalabra(line.split(",")[3]),
                            capitalizarPalabra(line.split(",")[4]),
                            LocalDate.now(),
                            zonaRepository.buscarPorProvincia(line.split(",")[5]).get(0),
                            leerContenidoFoto(line.split(",")[6], mimeType),
                            stringToCollectionInteres(line.split(",")[7]),
                            stringToCollectionDiscapacidad(line.split(",")[8]),
                            stringToCollectionNecesidad(line.split(",")[9]),
                            familiarRepository.buscarPorNombreApellido(capitalizarPalabra(line.split(",")[10]), capitalizarPalabra(line.split(",")[11])).get(0)
                    );

                    amigoRepository.save(amigo);
                } catch (Exception e) {
                    System.out.println("Error ejecutando la linea: " + line + "\r\nError: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearFamiliaresDefault() throws ParseException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("familiar.csv");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    FamiliarAcargo familiar = new FamiliarAcargo(
                            capitalizarPalabra(line.split(",")[0]),
                            capitalizarPalabra(line.split(",")[1]),
                            stringToInt(line.split(",")[2]),
                            capitalizarPalabra(line.split(",")[3]),
                            capitalizarPalabra(line.split(",")[4])
                    );
                    familiarRepository.save(familiar);
                } catch (Exception e) {
                    System.out.println("Error ejecutando la linea: " + line + "\r\nError: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private String capitalizarPalabra(String string) {
        String resultado = "";
        for (String p : string.split(" ")) {
            if (p.length() > 1) {
                resultado += " " + p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase();
            } else {
                resultado += " " + p.toUpperCase();
            }
        }
        return resultado.trim();
    }

    private int stringToInt(String string) {
        return Integer.parseInt(string);
    }

    private long stringToLong(String string) {
        return Long.parseLong(string);
    }

    private LocalDate stringToLocalDate(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.parse(string).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Collection<Interes> stringToCollectionInteres(String string) {
        Interes[] intereses = new Interes[string.split(":").length];
        for (int i = 0; i < string.split(":").length; i++) {
            intereses[i] = Interes.valueOf(string.split(":")[i]);
        }

        return Arrays.asList(intereses);
    }

    private Collection<Necesidad> stringToCollectionNecesidad(String string) {
        Necesidad[] necesidades = new Necesidad[string.split(":").length];
        for (int i = 0; i < string.split(":").length; i++) {
            necesidades[i] = Necesidad.valueOf(string.split(":")[i]);
        }
        return Arrays.asList(necesidades);
    }

    private Collection<Discapacidad> stringToCollectionDiscapacidad(String string) {
        Discapacidad[] Discapacidades = new Discapacidad[string.split(":").length];
        for (int i = 0; i < string.split(":").length; i++) {
            Discapacidades[i] = Discapacidad.valueOf(string.split(":")[i]);
        }
        return Arrays.asList(Discapacidades);
    }

    private Collection<InteresVoluntario> stringToCollectionInteresVoluntario(String string) {
        InteresVoluntario[] interesVoluntario = new InteresVoluntario[string.split(":").length];
        for (int i = 0; i < string.split(":").length; i++) {
            interesVoluntario[i] = InteresVoluntario.valueOf(string.split(":")[i]);
        }
        return Arrays.asList(interesVoluntario);
    }

    private byte[] leerContenidoFoto(String nombreArchivo, String mimeType) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("static/img/" + nombreArchivo);
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();) {
            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            mimeType = URLConnection.guessContentTypeFromStream(is);

            return buffer.toByteArray();

        } catch (IOException e) {
            System.out.println("Error cargando fotografia, nombre del archivo: " + nombreArchivo);
            //e.printStackTrace(); 
            return null;
        }
    }

}
