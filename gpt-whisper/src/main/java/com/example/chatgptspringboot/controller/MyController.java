package com.example.chatgptspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class MyController {



    @GetMapping("/audio")
    public String audio(@RequestParam String filePath){
        try {
            Path path = Paths.get("src", "main", "resources", "python", "demo.py");
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "py", path.toFile().getAbsolutePath()
            );

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // You can collect this output if needed
            }

            int exitCode = process.waitFor();
            System.out.println("Script exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "x";
    }
}
