/*
 * Reference: https://github.com/iamtatsuyamori/jjebank/blob/main/src/main/java/com/example/test/
 */

package com.example.test.config;

import com.example.test.scalardb.PdLoadInitialData;
import com.example.test.scalardb.PdSchemaLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==================== TestApplicationRunner ====================");
        PdSchemaLoader.run();
        System.out.println("MySchemaLoader.run(): Succeeded!");
        PdLoadInitialData.run();
        System.out.println("MyLoadInitialData.run(): Succeeded!");
    }
}
