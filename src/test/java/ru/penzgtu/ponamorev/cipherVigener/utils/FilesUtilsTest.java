package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FilesUtilsTest {
    private String root = new File(".").getAbsolutePath();
    private String resourcesFolder = root + "\\src\\main\\resources\\";

    @Test
    @Ignore
    public void checkExistingFile() {
        List<String> result = FileUtils.readFromFile(new File(resourcesFolder + "text.txt"), "Caesar",
                new Scanner(new ByteArrayInputStream("2\n".getBytes())));
        Assert.assertNotNull(result);
        for (String line : result) {
            System.out.println(line);
        }
    }
}