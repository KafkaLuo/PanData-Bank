/**
 * 
 *  References:
 *      https://scalardb.scalar-labs.com/docs/latest/schema-loader/
 *      https://github.com/iamtatsuyamori/jjebank/tree/main/src/main/java/com/example/test/scalardb
 */

package com.example.test.scalardb;

import com.scalar.db.schemaloader.SchemaLoader;
import com.scalar.db.schemaloader.SchemaLoaderException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PdSchemaLoader {
    public static void run() throws SchemaLoaderException {
        Path configFilePath = Paths.get("src/main/resources/scalardb.properties");
        Path schemaFilePath = Paths.get("src/main/resources/schema.json");
        Map<String, String> tableCreationOptions = new HashMap<>();
        boolean createCoordinatorTables = true; // whether to create the coordinator tables or not

        // Initialization
        SchemaLoader.load(configFilePath, schemaFilePath, tableCreationOptions, createCoordinatorTables);
    }
}
