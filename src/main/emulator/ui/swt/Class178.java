package emulator.ui.swt;

import org.eclipse.swt.events.*;
import emulator.*;

final class Class178 extends KeyAdapter
{
    private final Class38 aClass38_1448;
    
    Class178(final Class38 aClass38_1448) {
        super();
        this.aClass38_1448 = aClass38_1448;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        keyEvent.keyCode &= 0xFEFFFFFF;
        if (Class38.method376(this.aClass38_1448).getSelectionIndex() == 0 && Keyboard.method594(String.valueOf(keyEvent.keyCode)) != null) {
            Class38.method415(this.aClass38_1448).setText(Keyboard.method594(String.valueOf(keyEvent.keyCode)));
            Class38.method365()[12] = Class38.method415(this.aClass38_1448).getText().trim();
        }
    }
}
