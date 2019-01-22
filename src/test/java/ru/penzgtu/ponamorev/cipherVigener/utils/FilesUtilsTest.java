package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class FilesUtilsTest {

    @Test
    public void checkExistingFile() {
        String absolutePath = "C:\\Resources\\text.txt";
        List<String> result = FileUtils.readFromFile(new File(absolutePath));
        Assert.assertNotNull(result);
        for (String line : result) {
            System.out.println(line);
        }
    }
}