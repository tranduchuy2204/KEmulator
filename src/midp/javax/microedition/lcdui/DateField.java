package javax.microedition.lcdui;

import java.util.*;

public class DateField extends Item
{
    public static final int DATE = 1;
    public static final int TIME = 2;
    public static final int DATE_TIME = 3;
    private int anInt349;
    private Date aDate348;
    
    public DateField(final String s, final int n) {
        this(s, n, null);
    }
    
    public DateField(final String s, final int inputMode, final TimeZone timeZone) {
        super(s);
        this.setInputMode(inputMode);
    }
    
    public Date getDate() {
        return this.aDate348;
    }
    
    public void setDate(final Date aDate348) {
        this.aDate348 = aDate348;
    }
    
    public int getInputMode() {
        return this.anInt349;
    }
    
    public void setInputMode(final int anInt349) {
        if (anInt349 != 1 && anInt349 != 2 && anInt349 != 3) {
            throw new IllegalArgumentException();
        }
        this.anInt349 = anInt349;
    }
    
    protected void paint(final Graphics graphics) {
        super.paint(graphics);
    }
}
