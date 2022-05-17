package io.fullstack.server.service.implementation;

import io.fullstack.server.ServerStatusEnum.Status;
import io.fullstack.server.model.Server;
import io.fullstack.server.repository.ServerRepository;
import io.fullstack.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        log.info("Saving a new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> getAllServers(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server getServer(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server updateServer(Server server) {
        log.info("Updating a server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServer(Long id) {
        log.info("Deleteing a server by id: {}", id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }



    /*
    ---------------------------------------------------------
    Below are all the methods needed that
    aren't implemented in the interface.
    ---------------------------------------------------------
     */

    private String setServerImageUrl() {
        String[] imageNames = { "server1.png", "server2.png"};
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(2)])
                .toUriString();
    }
}
