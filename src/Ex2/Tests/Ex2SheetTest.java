package Ex2.Tests;

import Ex2.Ex2Sheet;
import Ex2.Ex2Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class Ex2SheetTest {

    @Test
    public void testSheetInitializationAndDefaultValues() {
        Ex2Sheet sheet = new Ex2Sheet(5, 5);
        assertEquals(5, sheet.width());
        assertEquals(5, sheet.height());

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assertions.assertEquals(Ex2Utils.EMPTY_CELL, sheet.get(i, j).getData());
            }
        }
    }


    @Test
    public void testSetAndRetrieveCellData() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 0, "10.0");
        assertEquals("10.0", sheet.get(0, 0).getData());

        sheet.set(1, 1, "Ariel");
        assertEquals("Ariel", sheet.get(1, 1).getData());

        sheet.set(2, 2, "=A0+B0");
        assertEquals("=A0+B0", sheet.get(2, 2).getData());
    }


    @Test
    public void testEmptyCell() {
        Ex2Sheet sheet = new Ex2Sheet(1, 1);
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(2, 2));
    }


    @Test
    public void testFormulaEvaluation() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 1, "1");
        sheet.set(0, 2, "2");
        sheet.set(2, 2, "=A1+A2");
        assertEquals("3.0", sheet.value(2, 2));
    }

    @Test
    public void testFormulaError() {
        Ex2Sheet sheet = new Ex2Sheet(1, 1);
        sheet.set(0, 0, "=INVALID");
        assertEquals(Ex2Utils.ERR_FORM, sheet.value(0, 0));
    }

    @Test
    public void testDetectCircularReferences() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 1, "=C2");
        sheet.set(2, 2, "=A1");
        assertEquals(Ex2Utils.ERR_CYCLE, sheet.value(0, 1));
        assertEquals(Ex2Utils.ERR_CYCLE, sheet.value(2, 2));
    }

    @Test
    public void testSaveLoad() throws IOException {
        Ex2Sheet sheet = new Ex2Sheet(4, 4);
        sheet.set(0, 0, "10");
        sheet.set(1, 1, "World");
        sheet.set(2, 2, "=A0*2");
        sheet.set(3, 3, "42");

        String fileName = "example_sheet.csv";
        sheet.save(fileName);

        Ex2Sheet loadedSheet = new Ex2Sheet(4, 4);
        loadedSheet.load(fileName);

        assertEquals("10.0", loadedSheet.get(0, 0).getData());
        assertEquals("World", loadedSheet.get(1, 1).getData());
        assertEquals("=A0*2", loadedSheet.get(2, 2).getData());
        assertEquals("42.0", loadedSheet.get(3, 3).getData());

        new File(fileName).delete();
    }

    @Test
    public void testDepthComputation() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 0, "=A0");
        sheet.set(1, 0, "10");

        int[][] depths = sheet.depth();
        assertEquals(1, depths[0][0]);
        assertEquals(0, depths[1][0]);
    }

    @Test
    public void testExceptionalCases() {
        Ex2Sheet sheet = new Ex2Sheet(1, 1);


        sheet.set(0, 0, null);
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.get(0, 0).getData());

        sheet.set(0, 0, "");
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.get(0, 0).getData());


        sheet.set(0, 0, "=A0");
        assertEquals(Ex2Utils.ERR_CYCLE, sheet.value(0, 0));
    }
}