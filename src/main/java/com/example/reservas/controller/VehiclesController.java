package com.example.reservas.controller;

import com.example.reservas.dto.*;
import com.example.reservas.entity.Vehicles;
import com.example.reservas.service.impl.VehiclesImpl;
import com.example.reservas.service.inter.KeycloakService;
import com.example.reservas.service.inter.MinioService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehiclesController {

    @Value("${minio.bucket-name}")
    private String bucket;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private VehiclesImpl vehiclesService;


    @GetMapping("/all")
    public ResponseEntity<List<VehiclesDto>> getAllVehicles() {
        try {
//            Map<String, String> data = Map.of(
//                    "grant_type", "client_credentials",
//                    "client_id", keycloakClient,
//                    "client_secret", "ZgBAyWoHLGK7nJjQnLPvjYPrfbYibFy3");
//            String token = "Bearer " + keycloakService.getToken(data).get("access_token");
//            log.info("Token: {}", token);
            return ResponseEntity.ok(vehiclesService.getAllVehicles());
        } catch (Exception e) {
            log.error("Error al obtener la lista de vehiculos", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicles> getVehicleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehiclesService.getVehicleById(id));
        } catch (Exception e) {
            log.error("Error al obtener el vehiculo", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("all_by_customer/{id}")
    public ResponseEntity<List<Vehicles>> getVehiclesByCustomer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehiclesService.getVehiclesByCustomer(id));
        } catch (Exception e) {
            log.error("Error al obtener el vehiculo", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveVehicles(@RequestBody VehiclesDto vehiclesDto) {
        try {
            log.info("Vehiculo a guardar: {}", vehiclesDto);
            vehiclesService.saveVehicles(vehiclesDto);
            return ResponseEntity.ok("Vehiculo guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al guardar el vehiculo");
        }
    }

    @PostMapping("/save_both")
    public ResponseEntity<String> saveVehiclesAndCustomer(@RequestParam("file") MultipartFile file, @RequestParam("data") String vehicles) {
        try {
            MultipartFile multipartFile = file;
            // imprime el nombre del archivo
            String etag = uploadFile(multipartFile);
            log.info("Vehiculo en String: {}", vehicles);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(vehicles);

            VehiclesDtoSpecial vehiclesDtoSpecial = new VehiclesDtoSpecial();
            CustomerDtoSpecial customerDtoSpecial = new CustomerDtoSpecial();
            PersonDto personDto = new PersonDto();
            UserDto userDto = new UserDto();

            vehiclesDtoSpecial.setBrand(jsonNode.get("brand").asText());
            vehiclesDtoSpecial.setColor(jsonNode.get("color").asText());
            vehiclesDtoSpecial.setLicensePlate(jsonNode.get("licensePlate").asText());
            vehiclesDtoSpecial.setEtag(etag);

            JsonNode jsonNodeCustomer = jsonNode.get("customer");
            JsonNode jsonNodePerson = jsonNodeCustomer.get("person");
            JsonNode jsonNodeUser = jsonNodePerson.get("user");

            personDto.setName(jsonNodePerson.get("name").asText());
            personDto.setSurname(jsonNodePerson.get("surname").asText());
            personDto.setDni(jsonNodePerson.get("dni").asText());
            personDto.setEmail(jsonNodePerson.get("email").asText());
            personDto.setPhone(jsonNodePerson.get("phone").asText());

            userDto.setUsername(jsonNodeUser.get("username").asText());
            userDto.setPassword(jsonNodeUser.get("password").asText());

            personDto.setUser(userDto);
            customerDtoSpecial.setPerson(personDto);
            vehiclesDtoSpecial.setCustomer(customerDtoSpecial);

            vehiclesService.saveVehiclesAndCustomer(vehiclesDtoSpecial);
            return ResponseEntity.ok("Vehiculo guardado correctamente");
        } catch (Exception e) {
            log.error("Error al guardar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al guardar el vehiculo");
        }
    }

    public String uploadFile(MultipartFile file) {
        Date date = new Date();
        // obtiene la fecha y hora actual para ponerle un nombre al archivo
        String fileName = date.toString().replace(" ", "_") + "_" + file.getOriginalFilename();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
            // consigue el etag del archivo que se acaba de subir
            String etag = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(file.getOriginalFilename())
                            .build()
            ).etag();
            return etag;
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }

    @GetMapping("/download/{etag}")
    public ResponseEntity<String> getDownloadUrl(@PathVariable String etag) {
        try {
            return ResponseEntity.ok(getDownloadUrl1(etag));
        } catch (Exception e) {
            log.error("Error al obtener el vehiculo", e);
            return ResponseEntity.badRequest().build();
        }
    }
    public String getDownloadUrl1(@PathVariable String etag) {
        // obten el nombre del archivo con el etag, sin tener que hacer un listado de todos los archivos
        try {
            String bucketName = bucket;

            Iterable<Result<Item>> myObjects = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            for (Result<Item> result : myObjects) {
                Item item = result.get();
                String etag1 = item.etag().substring(1, item.etag().length() - 1);

                if (etag1.equals(etag)) {
                    return "File URL: " + minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(bucketName)
                                    .object(item.objectName())
                                    .expiry(1, TimeUnit.DAYS)
                                    .build());
                }
            }
            return "File URL: " + "No se encontro el archivo";
        } catch (MinioException e) {
            e.printStackTrace();
            return "Error getting file URL: " + e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicles(@PathVariable Long id) {
        try {
            vehiclesService.deleteVehicles(id);
            return ResponseEntity.ok("Vehiculo eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar el vehiculo", e);
            return ResponseEntity.badRequest().body("Error al eliminar el vehiculo");
        }
    }
}

/* haz un dato de prueba para insertar con postman
{
    "id": 1,
    "brand": "Toyota",
    "model": "Corolla",
    "color": "Rojo",
    "licensePlate": "ABC123",
    "customer": 1
}
*/
