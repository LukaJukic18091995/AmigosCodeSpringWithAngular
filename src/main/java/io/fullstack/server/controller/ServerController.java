package io.fullstack.server.controller;

import io.fullstack.server.ServerStatusEnum.Status;
import io.fullstack.server.model.Response;
import io.fullstack.server.model.Server;
import io.fullstack.server.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImplementation serverService;

    /*
    Method used for retrieving all the servers in a list.
     */
    @GetMapping("/server-list")
    public ResponseEntity<Response> getServers() {
        int limit = 30;
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.getAllServers(limit)))
                        .successMessage("Servers are listed.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /*
    Method used for pinging a server.
    */
    @GetMapping("/ping-server/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.pingServer(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .successMessage(server.getStatus() == Status.SERVER_UP ? "Ping success!" : "Ping failed!")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /*
    Method used for saving a new server to the DB
     */
    @PostMapping("/save-server")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.createServer(server)))
                        .successMessage("Server created!")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    /*
    Method used for fetching a server.
    */
    @GetMapping("/server/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.getServer(id)))
                        .successMessage("Server retrieved.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /*
    Method used for deleting a server.
    */
    @DeleteMapping("/delete-server/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("deleted", serverService.deleteServer(id)))
                        .successMessage("Server deleted.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /*
    Method used for fetching an image for a server.
    */
    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("jukicluk.home")
                + "angular/fullstackServer/server_images/" + fileName));
    }


}
