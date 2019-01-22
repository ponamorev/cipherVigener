package ru.penzgtu.ponamorev.cipherVigener.utils;

import org.testng.annotations.Test;

import java.io.File;

public class FilesUtilsTest {

    @Test
    public void checkExistingFile() {
        String absolutePath = "C:\\Users\\andersen\\Desktop\\text.txt";
        FileUtils.readFromFile(new File(absolutePath));
    }
}