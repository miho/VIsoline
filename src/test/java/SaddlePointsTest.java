import org.junit.Test;

import eu.mihosoft.visoline.Data_float;
import eu.mihosoft.visoline.MarchingSquares_float;
import eu.mihosoft.visoline.Path_float;
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
}
