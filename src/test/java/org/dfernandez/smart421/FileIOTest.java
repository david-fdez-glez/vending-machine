package org.dfernandez.smart421;

import org.dfernandez.smart421.util.FilesUtil;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class FileIOTest {

    static final String COIN_INVENTORY_READ_PATH = "src/test/resources/coin-inventory-read.properties";
    static final String COIN_INVENTORY_WRITE_PATH = "src/test/resources/coin-inventory-write.properties";

    List<String> expectedLines;


    @Before
    public void init() {

    }

    @Test
    public void testFileExists() {
        File file = new File(COIN_INVENTORY_READ_PATH);
        assertEquals(true,file.exists());
    }

    @Test
    public void testReadTextFileLines() {
        List<String> lines = FilesUtil.readAllLines(COIN_INVENTORY_READ_PATH);

        expectedLines = new ArrayList<>();
        expectedLines.add("100=1");
        expectedLines.add("50=2");
        expectedLines.add("20=3");
        expectedLines.add("10=4");
        expectedLines.add("5=5");
        expectedLines.add("2=6");
        expectedLines.add("1=7");

        assertThat(expectedLines, equalTo(lines));

    }


    @Test
    public void testWriteTextFile() {

        expectedLines = new ArrayList<>();
        expectedLines.add("100=25");
        expectedLines.add("50=23");
        expectedLines.add("20=4");
        expectedLines.add("10=0");
        expectedLines.add("5=1");
        expectedLines.add("2=45");
        expectedLines.add("1=10");

        StringBuilder sb = new StringBuilder();
        sb.append("100=25\n").append("50=23\n").append("20=4\n").append("10=0\n").append("5=1\n").append("2=45\n").append("1=10\n");

        FilesUtil.writeToTextFile(COIN_INVENTORY_WRITE_PATH,sb.toString());

        List<String> lines = FilesUtil.readAllLines(COIN_INVENTORY_WRITE_PATH);

        assertThat(expectedLines, equalTo(lines));

    }

}
