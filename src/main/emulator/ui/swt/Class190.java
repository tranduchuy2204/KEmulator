package emulator.ui.swt;

import org.eclipse.swt.events.*;
import emulator.*;
import emulator.graphics2D.swt.*;
import emulator.graphics2D.swt.ImageSWT;

final class Class190 implements PaintListener
{
    private final Class38 aClass38_1483;
    
    Class190(final Class38 aClass38_1483) {
        super();
        this.aClass38_1483 = aClass38_1483;
    }
    
    public final void paintControl(final PaintEvent paintEvent) {
        if (Class38.method356(this.aClass38_1483) == null) {
            Class38.method369(this.aClass38_1483, 1);
        }
        if (Settings.g2d == 0) {
            ((ImageSWT)Class38.method356(this.aClass38_1483)).method12(paintEvent.gc, 0, 0);
            return;
        }
        if (Settings.g2d == 1) {
            ((emulator.graphics2D.awt.d)Class38.method356(this.aClass38_1483)).method12(paintEvent.gc, 0, 0);
        }
    }
}
