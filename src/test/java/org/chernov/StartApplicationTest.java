//package org.chernov;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import static org.junit.Assert.assertTrue;
//
//public class StartApplicationTest {
//
//    private final String testDir = "test-files";
//
//    @Before
//    public void setUp() {
//
//        new File(testDir).mkdir();
//    }
//
//    @Test
//    public void testProcessFiles() throws IOException {
//
//        File testFile = new File(testDir, "test.txt");
//        try (FileWriter writer = new FileWriter(testFile)) {
//            writer.write("123\n456\nHello\n78.9\nWorld\n");
//        }
//
//
//        String[] args = {"-o", "output", "-p", "result_", testFile.getAbsolutePath()};
//        StartApplication.main(args);
//
//
//        File integersFile = new File("output/result_integers.txt");
//        File floatsFile = new File("output/result_floats.txt");
//        File stringsFile = new File("output/result_strings.txt");
//
//        assertTrue(integersFile.exists());
//        assertTrue(floatsFile.exists());
//        assertTrue(stringsFile.exists());
//
//    }
//
//    @After
//    public void tearDown() {
//        for (File file : new File(testDir).listFiles()) {
//            file.delete();
//        }
//        new File(testDir).delete();
//
//        // Удаление выходных файлов (если необходимо)
//        new File("output").delete();
//    }
//}