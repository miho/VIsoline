/*
 * Copyright 2014-2017 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Michael Hoffer <info@michaelhoffer.de> "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Michael Hoffer <info@michaelhoffer.de> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Michael Hoffer <info@michaelhoffer.de>.
 */
package eu.mihosoft.visoline;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class MetaCircleWorld {

    private MarchingSquares_float ms;
    private final List<MetaCircle> metaCircles = new ArrayList<>();
    private Data_float forceField;
    private double scaleX = 1.0;
    private double scaleY = 1.0;

    public MetaCircleWorld(int width, int height, double scaleX, double scaleY) {
        forceField = new Data_float(width, height);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        ms = new MarchingSquares_float(forceField);
    }

    public List<Path_float> update() {

        for (int y = 0; y < forceField.getHeight(); y++) {
            for (int x = 0; x < forceField.getWidth(); x++) {
                forceField.set(0, x, y);
                for (int i = 0; i < metaCircles.size(); i++) {
                    float val = (float) metaCircles.get(i).getStrengthAt(x, y);
                    forceField.plus(val, x, y);
                }
            }
        }
        
        List<Path_float> paths = new ArrayList<>();
        float dt = 0.125f;
        for(int i = 0; i < 5;i++) {
            paths.add(ms.computePaths((i+1)*dt));
        }
        
        return paths;
    }

    public void resize(int width, int height, double scaleX, double scaleY) {
        forceField = new Data_float(width, height);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        ms = new MarchingSquares_float(forceField);
    }

    public void addMetaCircle(MetaCircle mc) {
        metaCircles.add(mc);
    }

    public boolean removeMetaCircle(MetaCircle mc) {
        return metaCircles.remove(mc);
    }

    public Data_float getForceField() {
        return forceField;
    }
}
