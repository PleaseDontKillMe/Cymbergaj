package danon.Cymbergaj;


import danon.Cymbergaj.Geometry.Size;

public class Settings {
    private final Size size;
    private final String windowTitle;

    public Settings(String windowTitle, Size size) {
        this.size = size;
        this.windowTitle = windowTitle;
    }

    public Size getSize() {
        return size;
    }

    public String getWindowTitle() {
        return windowTitle;
    }
}
