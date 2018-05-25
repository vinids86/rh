package com.xerpa.acme.utils.file;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class FileUtils {

    public String getFileFromClasspath(String fileName) throws URISyntaxException, IOException {
        URI resourceURI = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI();
        Path path;

        if (resourceURI.toString().contains("!")) {
            String[] URIparts = resourceURI.toString().split("!");
            URI URIjars = URI.create(URIparts[0]);
            FileSystem fs;
            try {
                fs = FileSystems.newFileSystem(URIjars, new HashMap<String, String>());
            } catch (FileSystemAlreadyExistsException e) {
                fs = FileSystems.getFileSystem(URIjars);
            }
            path = fs.getPath(URIparts[1]);
        } else {
            path = Paths.get(resourceURI);
        }

        StringBuilder data = new StringBuilder();
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> data.append(line).append("\n"));
        lines.close();

        return data.toString();
    }
}
