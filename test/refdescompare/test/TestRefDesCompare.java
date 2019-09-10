package refdescompare.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import refdescompare.RefDesCompare;

class TestRefDesCompare
{

    @BeforeEach
    void setUp() throws Exception
    {
    }

    @AfterEach
    void tearDown() throws Exception
    {
    }

    @Test
    void testRefDesCompare()
    {
        testRefDesCompareCombos(null, "A");

        //single letter & digit
        testRefDesCompareCombos("A", "B");
        testRefDesCompareCombos("A1", "B1");
        testRefDesCompareCombos("A1", "A2");
        testRefDesCompareCombos("A1A", "A1B");
        testRefDesCompareCombos("A1A1", "A1A2");

        //multi letter / single digit
        testRefDesCompareCombos("ABC", "ABZ");
        testRefDesCompareCombos("ABC1", "ABZ1");
        testRefDesCompareCombos("ABC1", "ABC2");
        testRefDesCompareCombos("ABC1ABC", "ABC1ABZ");
        testRefDesCompareCombos("ABC1ABC1", "ABC1ABC2");

        //single letter / multi digit
        testRefDesCompareCombos("A123", "B123");
        testRefDesCompareCombos("A123", "A129");
        testRefDesCompareCombos("A123A", "A123B");
        testRefDesCompareCombos("A123A123", "A123A129");

        //multi letter & digit
        testRefDesCompareCombos("ABC123", "ABZ123");
        testRefDesCompareCombos("ABC123", "ABC129");
        testRefDesCompareCombos("ABC123ABC", "ABC123ABZ");
        testRefDesCompareCombos("ABC123ABC123", "ABC123ABC129");
        
        //different length strings
        testRefDesCompareCombos("A", "AB");
        testRefDesCompareCombos("A1", "AB1");
        testRefDesCompareCombos("A1", "A10");
        testRefDesCompareCombos("A1A", "A1AB");
        testRefDesCompareCombos("A1A1", "A1A10");
        
        // should ignore case
        assertTrue(RefDesCompare.compare("r10", "R10") == 0);
        assertTrue(RefDesCompare.compare("R10", "r10") == 0);

    }

    void testRefDesCompareCombos(String lesser, String greater)
    {
        assertTrue(RefDesCompare.compare(lesser, greater) < 0);
        assertTrue(RefDesCompare.compare(greater, lesser) > 0);

        assertTrue(RefDesCompare.compare(lesser, lesser) == 0);
        assertTrue(RefDesCompare.compare(greater, greater) == 0);
    }

}
