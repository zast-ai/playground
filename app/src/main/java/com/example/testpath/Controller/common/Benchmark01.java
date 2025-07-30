package com.example.testpath.Controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

@Controller
@RequestMapping("/benchmark01")
public class Benchmark01 {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/readString")
    @ResponseBody
    public String readFileToString(String filepath) {
        File file = new File(filepath);
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + e.getMessage();
        }
    }
}
