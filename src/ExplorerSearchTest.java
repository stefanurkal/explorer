import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    @Test
    public void test_startInACrater() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,3,3,1},
            {1,1,3,0,3,3},
            {3,1,3,3,3,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        int[] location = {2, 3}; 

        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);
        assertEquals(1, actual);
        assertTrue(moveSet.isEmpty());
    
    }

    @Test
    public void test_startInAIslandOnanIsland() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,2,2,2,1},
            {1,1,2,2,2,3},
            {3,1,2,2,2,1},
            {1,1,1,2,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.startLocation(island);
        });
        assertEquals("No explorers are on the island", exception.getMessage());
        
    }


    @Test
    public void test_allDirections() {
        int[][] island = {
            {3,3,3,3,3,3},
            {3,2,2,1,2,3},
            {3,2,1,0,1,3},
            {3,2,2,1,2,1},
            {3,3,3,3,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        int[] location = {2, 3}; 
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(5, actual);
        
        assertTrue(moveSet.contains("2,2"));
        assertTrue(moveSet.contains("2,4"));
        assertTrue(moveSet.contains("1,3"));
        assertTrue(moveSet.contains("3,3"));
    }

    @Test 
    public void test_outofbounds() {
        int[][] island = {
            {0,1,1,1,3,3},
            {1,2,2,1,2,3},
            {3,2,1,0,1,3},
            {3,2,2,1,2,1},
            {3,3,3,3,1,1},
        };
        
        int[] location = {0, 0}; 
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);
       
        assertFalse(moveSet.contains("-1,0"));
        assertFalse(moveSet.contains("0, -1"));

    }
    @Test
    public void test_outofbounds2() {
        int[][] island = {
            {1,1,1,1,3,3},
            {1,2,2,1,2,3},
            {3,2,1,0,1,3},
            {3,2,2,1,2,1},
            {3,3,3,3,1,0},
        };
       
        int[] location = {4, 5}; 
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);
       
        assertFalse(moveSet.contains("5,5"));
        assertFalse(moveSet.contains("4, 6"));

    }
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}


