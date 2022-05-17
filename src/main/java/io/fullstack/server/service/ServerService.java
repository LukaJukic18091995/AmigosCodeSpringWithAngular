package io.fullstack.server.service;

import io.fullstack.server.model.Server;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    /*
    Initialization of a method that will create a new server
     */
    Server createServer(Server server);

    /*
    Initialization of a method that will return a collection/list
    filled with as many servers as the limit parameter equals
     */
    Collection<Server> getAllServers(int limit);

    /*
    Initialization of a method that will return an existing server
     */
    Server getServer(Long id);

    /*
    Initialization of a method that will update an existing server
     */
    Server updateServer(Server server);

    /*
    Initialization of a method that will delete an existing server
     */
    Boolean deleteServer(Long id);

    /*
    Initialization of a method that will ping the server

     */
    Server pingServer(String ipAddress) throws IOException;
}
