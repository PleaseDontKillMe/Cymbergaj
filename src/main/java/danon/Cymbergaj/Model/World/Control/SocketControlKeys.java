package danon.Cymbergaj.Model.World.Control;

import java.awt.event.KeyEvent;

public class SocketControlKeys extends ControlKeys {
    @Override
    public boolean verifyUp(KeyEvent event) {
        return false;
    }

    @Override
    public boolean verifyDown(KeyEvent event) {
        return false;
    }

    @Override
    public boolean verifyLeft(KeyEvent event) {
        return false;
    }

    @Override
    public boolean verifyRight(KeyEvent event) {
        return false;
    }

    public void acceptKeyChange(String response) {
        System.out.println("Get key " + response);
        switch (response) {
            case "KEYS UP____0":
                keys.setUp(false);
                break;
            case "KEYS DOWN__0":
                keys.setDown(false);
                break;
            case "KEYS LEFT__0":
                keys.setLeft(false);
                break;
            case "KEYS RIGHT_0":
                keys.setRight(false);
                break;
            case "KEYS UP____1":
                keys.setUp(true);
                break;
            case "KEYS DOWN__1":
                keys.setDown(true);
                break;
            case "KEYS LEFT__1":
                keys.setLeft(true);
                break;
            case "KEYS RIGHT_1":
                keys.setRight(true);
                break;
            default:
                System.out.println("Inny komunikat: " + response);
        }
    }
}