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

    @Test
    @Ignore
    public void checkExistingFile() {
        String absolutePath = new File(".").getAbsolutePath() + "\\src\\main\\resources\\text.txt";
        List<String> result = FileUtils.readFromFile(new File(absolutePath), "Caesar",
                new Scanner(new ByteArrayInputStream("2\n".getBytes())));
        Assert.assertNotNull(result);
        for (String line : result) {
            System.out.println(line);
        }
    }
}