package eu.mihosoft.visoline;

public class Path_int {

    private final int isoValue;

    private final java.util.List<java.util.List<Vector2d>> contours;
    private java.util.List<Vector2d> vertices;

    private final BSpline optimizer = new BSpline();

    public Path_int(int isoValue) {
        contours = new java.util.ArrayList<>();
        vertices = new java.util.ArrayList<>();
        contours.add(vertices);
        this.isoValue = isoValue;
    }

    public int getNumberOfContours() {
        return contours.size();
    }

    public java.util.List<Vector2d> getContour(int i) {
        return contours.get(i);
    }

    public void moveTo(double x, double y) {
        moveTo(new Vector2d(x, y));
    }

    public void moveTo(Vector2d p) {
        breakContour();
        vertices.add(p);

    }

    public void lineTo(Vector2d p) {
        vertices.add(p);
    }

    public void lineTo(double x, double y) {
        lineTo(new Vector2d(x, y));
    }



    public void breakContour() {
        if (!vertices.isEmpty()) {
            vertices = new java.util.ArrayList<>();
            contours.add(vertices);
        }
    }

        public int getIsoValue() {
        return isoValue;
    }

    public boolean isEmpty() {
        return contours.isEmpty() || contours.stream().allMatch(verts -> verts.isEmpty());
    }

    public javafx.scene.shape.Path toJavaFXPath(boolean optimized, double scaleX, double scaleY) {

        javafx.scene.shape.Path jfxPath = new javafx.scene.shape.Path();

        if (this.isEmpty()) {
            return jfxPath;
        }

        for (int i = 0; i < this.getNumberOfContours(); i++) {
            java.util.List<Vector2d> path = this.getContour(i);
            if (path.isEmpty()) {
                continue;
            }

            if (optimized) {
                path = optimizer.convert(path, true);
            }

            Vector2d firstP = path.get(0);
            jfxPath.getElements().add(
                    new javafx.scene.shape.MoveTo(firstP.x * scaleX, firstP.y * scaleY));
            for (int j = 1; j < path.size(); j++) {

                Vector2d p = path.get(j);

                jfxPath.getElements().add(
                        new javafx.scene.shape.LineTo(p.x * scaleX, p.y * scaleY));
            }

        }

        jfxPath.getElements().add(new javafx.scene.shape.ClosePath());

        return jfxPath;
    }

    public javafx.scene.shape.Path toJavaFXPath(boolean optimized) {
        return toJavaFXPath(optimized, 1, 1);
    }

    public javafx.scene.shape.Path toJavaFXPath(double scaleX, double scaleY) {
        return toJavaFXPath(true, scaleX, scaleY);
    }

}
