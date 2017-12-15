import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import eu.mihosoft.visoline.Data_float;
import eu.mihosoft.visoline.MarchingSquares_float;
import eu.mihosoft.visoline.Path_float;
import eu.mihosoft.visoline.Vector2d;
import junit.framework.Assert;

public class SaddlePointsTest {
    @Test
    public void testSaddle() {
        Data_float data = new Data_float(new float[] {
                0,   0,   0,   0,
                0,   2,   .9f, 0,
                0,   .9f, 2,   0,
                0,   0,   0,   0
            }, 4, 4);
        MarchingSquares_float marching = new MarchingSquares_float(data);
        Path_float paths = marching.computePaths(1);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testSaddle2() {
        Data_float data = new Data_float(new float[] {
                0, 0, 0, 0,
                0, .9f, 2, 0,
                0, 2, .9f, 0,
                0, 0, 0, 0
            }, 4, 4);
        MarchingSquares_float marching = new MarchingSquares_float(data);
        Path_float paths = marching.computePaths(1);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testSaddle3() {
        Data_float data = new Data_float(new float[] {
                0, 0, 0, 0,
                0, 0, 1.5f, 0,
                0, 1.5f, 0, 0,
                0, 0, 0, 0
            }, 4, 4);
        MarchingSquares_float marching = new MarchingSquares_float(data);
        Path_float paths = marching.computePaths(1);
        Assert.assertEquals(2, paths.getNumberOfContours());
    }

    @Test
    public void testSaddle4() {
        Data_float data = new Data_float(new float[] {
                0, 0, 0, 0,
                0, 1.5f, 0, 0,
                0, 0, 1.5f, 0,
                0, 0, 0, 0
            }, 4, 4);
        MarchingSquares_float marching = new MarchingSquares_float(data);
        Path_float paths = marching.computePaths(1);
        Assert.assertEquals(2, paths.getNumberOfContours());
    }

    @Test
    public void testEdgesHigh() {
        Data_float data = new Data_float(new float[] {
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 0, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
            }, 5, 5);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.5f);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testEdgesHigh2() {
        Data_float data = new Data_float(new float[] {
                1, 1, 0, 1, 1,
                1, 1, 0, 1, 1,
                1, 1, 0, 1, 1,
                1, 1, 0, 1, 1,
                1, 1, 0, 1, 1,
            }, 5, 5);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.5f);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testEdgesHigh3() {
        Data_float data = new Data_float(new float[] {
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                0, 0, 0, 0, 0,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
            }, 5, 5);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.5f);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testEdgesHigh4() {
        Data_float data = new Data_float(new float[] {
                1, 1, 1, 1, 0,
                1, 1, 1, 0, 1,
                1, 1, 0, 1, 1,
                1, 0, 1, 1, 1,
                0, 1, 1, 1, 1,
            }, 5, 5);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.9f);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testEdgesHigh5() {
        Data_float data = new Data_float(new float[] {
                0, 1, 1, 1, 1,
                1, 0, 1, 1, 1,
                1, 1, 0, 1, 1,
                1, 1, 1, 0, 1,
                1, 1, 1, 1, 0,
            }, 5, 5);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.9f);
        Assert.assertEquals(1, paths.getNumberOfContours());
    }

    @Test
    public void testEdgeInterp() {
        Data_float data = new Data_float(new float[] {
                5, 5, 5,
                5, 1, 5,
                5, 5, 5,
            }, 3, 3);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(10);
        Assert.assertEquals(1, paths.getNumberOfContours());
        for (Vector2d p : paths.getContour(0)) {
            Assert.assertTrue(p.y >= 0 && p.y < 2);
            Assert.assertTrue(p.x >= 0 && p.x < 2);
        }
    }

    @Test
    public void testWinding() {
        Data_float data = new Data_float(new float[] {
                0, 0, 0,
                0, 1, 0,
                0, 0, 0,
            }, 3, 3);
        MarchingSquares_float marching = new MarchingSquares_float(data);
        Path_float paths = marching.computePaths(.5f);
        Assert.assertEquals(1, paths.getNumberOfContours());

        // Lines always surround the high values using a standard CCW polygon winding
        ArrayList<Vector2d> expectedEdge = new ArrayList<>(Arrays.asList(
                new Vector2d(1.0, 0.5),
                new Vector2d(1.5, 1.0)));
        List<Vector2d> contour = new ArrayList<>(paths.getContour(0));
        contour.addAll(paths.getContour(0));
        for (int i = 0; i < 4; i++) {
            if (contour.get(i).equals(expectedEdge.get(0))) {
                Assert.assertEquals(expectedEdge.get(1), contour.get(i+1));
            }
        }
    }

    @Test
    public void testWinding2() {
        Data_float data = new Data_float(new float[] {
                1, 1, 1,
                1, 0, 1,
                1, 1, 1,
            }, 3, 3);
        MarchingSquares_float marching = new MarchingSquares_float(data, false);
        Path_float paths = marching.computePaths(.5f);
        Assert.assertEquals(1, paths.getNumberOfContours());

        // Lines always surround the high values using a standard CCW polygon winding
        ArrayList<Vector2d> expectedEdge = new ArrayList<>(Arrays.asList(
                new Vector2d(1.0, 0.5),
                new Vector2d(0.5, 1.0)));
        List<Vector2d> contour = new ArrayList<>(paths.getContour(0));
        contour.addAll(paths.getContour(0));
        for (int i = 0; i < 4; i++) {
            if (contour.get(i).equals(expectedEdge.get(0))) {
                Assert.assertEquals(expectedEdge.get(1), contour.get(i+1));
            }
        }
    }

}
