package emulator.ui.swt;

import org.eclipse.swt.events.*;
import emulator.*;

final class Class173 extends KeyAdapter
{
    private final Class38 aClass38_1442;
    
    Class173(final Class38 aClass38_1442) {
        super();
        this.aClass38_1442 = aClass38_1442;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        keyEvent.keyCode &= 0xFEFFFFFF;
        if (Class38.method376(this.aClass38_1442).getSelectionIndex() == 0 && Keyboard.method594(String.valueOf(keyEvent.keyCode)) != null) {
            Class38.method397(this.aClass38_1442).setText(Keyboard.method594(String.valueOf(keyEvent.keyCode)));
            Class38.method365()[6] = Class38.method397(this.aClass38_1442).getText().trim();
        }
    }
}
