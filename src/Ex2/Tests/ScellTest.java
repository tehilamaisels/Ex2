package Ex2.Tests;


import Ex2.Ex2Utils;
import Ex2.SCell;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ScellTest {

    @Test
    public void testCellCreationAndDataAccess() {
        SCell cellInstance = new SCell("Ariel");
        assertEquals("Ariel", cellInstance.getData());
    }

    @Test
    public void testTextTypeDetection() {
        SCell cellInstance = new SCell("Ariel");
        Assert.assertEquals(Ex2Utils.TEXT, cellInstance.getType());
    }

    @Test
    public void testNumberTypeDetection() {
        SCell cellInstance = new SCell("1.2");
        assertEquals(Ex2Utils.NUMBER, cellInstance.getType());
    }

    @Test
    public void testFormulaTypeDetection() {
        SCell cellInstance = new SCell("=1+1");
        assertEquals(Ex2Utils.FORM, cellInstance.getType());
    }

    @Test
    public void testSetDataAndVerifyUpdate() {
        SCell cellInstance = new SCell("Ariel");
        cellInstance.setData("Ariel");
        assertEquals("Ariel", cellInstance.getData());
    }

    @Test
    public void testValidFormulaComputation() {
        SCell cellInstance = new SCell("=2-1");
        assertEquals(1.0, SCell.computeForm(cellInstance.getData()), 0.01);
    }

    @Test
    public void testInvalidFormulaComputation() {
        SCell cellInstance = new SCell("=1++1");
        Exception thrownError = assertThrows(IllegalArgumentException.class, () -> SCell.computeForm(cellInstance.getData()));
        assertTrue(thrownError.getMessage().contains("Invalid formula"));
    }

    @Test
    public void verifyNumberValidationLogic() {
        assertTrue(SCell.isNumber("123"));
        assertTrue(SCell.isNumber("0.123"));
        assertFalse(SCell.isNumber("Test"));
    }

    @Test
    public void verifyTextValidationLogic() {
        assertTrue(SCell.isText("Text"));
        assertFalse(SCell.isText("123"));
        assertFalse(SCell.isText("=1+2"));
    }

    @Test
    public void verifyFormulaValidationLogic() {
        assertTrue(SCell.isForm("=1+1"));
        assertFalse(SCell.isForm("Text"));
        assertFalse(SCell.isForm("123"));
        assertFalse(SCell.isForm("=1++1"));
    }

    @Test
    public void testOrderSetAndGet() {
        SCell cellInstance = new SCell("cellData");
        cellInstance.setOrder(10);
        assertEquals(10, cellInstance.getOrder());
    }

    @Test
    public void testToStringRepresentation() {
        SCell cellInstance = new SCell("Test");
        assertEquals("Test", cellInstance.toString());
    }
}
