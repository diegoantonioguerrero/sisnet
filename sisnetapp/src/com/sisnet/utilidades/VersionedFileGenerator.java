package com.sisnet.utilidades;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VersionedFileGenerator {

    // Singleton para almacenar hashes de archivos
    private static Map<String, String> fileHashCache = Collections.synchronizedMap(new HashMap<String, String>());

    // Método estático para obtener el hash versionado de un archivo
    public static String getVersionedHash(String filePath) throws IOException, NoSuchAlgorithmException {
        if (fileHashCache.containsKey(filePath)) {
            return fileHashCache.get(filePath);
        }

        String hash = computeFileHash(filePath);
        fileHashCache.put(filePath, hash);
        return hash;
    }

    // Calcula el hash SHA-256 del archivo
    private static String computeFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
    	// Obtener el timestamp en segundos
        long timestampInSeconds = Instant.now().getEpochSecond();
        // Convertir a String
        return Long.toString(timestampInSeconds);
    }

    // Limpia el caché de hashes
    public static void clearCache() {
        fileHashCache.clear();
    }

    // Devuelve el mapa del caché (para depuración)
    public static Map<String, String> getCache() {
        return fileHashCache;
    }
}
