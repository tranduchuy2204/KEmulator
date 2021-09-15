package javax.microedition.lcdui;

import emulator.lcdui.*;

public class ImageItem extends Item
{
    public static final int LAYOUT_DEFAULT = 0;
    public static final int LAYOUT_LEFT = 1;
    public static final int LAYOUT_RIGHT = 2;
    public static final int LAYOUT_CENTER = 3;
    public static final int LAYOUT_NEWLINE_BEFORE = 256;
    public static final int LAYOUT_NEWLINE_AFTER = 512;
    private Image anImage427;
    private String aString25;
    private int anInt349;
    
    public ImageItem(final String s, final Image image, final int n, final String s2) {
        this(s, image, n, s2, 0);
    }
    
    public ImageItem(final String s, final Image anImage427, final int anInt178, final String aString25, final int anInt179) {
        super(s);
        if (anInt179 < 0 || anInt179 > 2) {
            throw new IllegalArgumentException();
        }
        this.anImage427 = anImage427;
        super.anInt178 = anInt178;
        this.aString25 = aString25;
        this.anInt349 = anInt179;
    }
    
    public Image getImage() {
        return this.anImage427;
    }
    
    public void setImage(final Image anImage427) {
        this.anImage427 = anImage427;
    }
    
    public String getAltText() {
        return this.aString25;
    }
    
    public void setAltText(final String aString25) {
        this.aString25 = aString25;
    }
    
    public int getLayout() {
        return super.getLayout();
    }
    
    public void setLayout(final int layout) {
        super.setLayout(layout);
    }
    
    public int getAppearanceMode() {
        return this.anInt349;
    }
    
    protected void paint(final Graphics graphics) {
        super.paint(graphics);
        final int n = super.anIntArray21[0] + 2;
        int n2 = super.anIntArray21[1] + 2;
        if (super.aStringArray175 != null && super.aStringArray175.length > 0) {
            graphics.setFont(Item.aFont173);
            for (int i = 0; i < super.aStringArray175.length; ++i) {
                graphics.drawString(super.aStringArray175[i], super.anIntArray21[0] + 4, n2 + 2, 0);
                n2 += Item.aFont173.getHeight() + 4;
            }
        }
        graphics.drawImage(this.anImage427, n, n2, 0);
    }
    
    protected void layout() {
        super.layout();
        int n = 0;
        final int n2 = this.getPreferredWidth() - 8;
        if (super.aString172 != null) {
            super.aStringArray175 = c.method175(super.aString172, Item.aFont173, n2, n2);
            n = 0 + (Item.aFont173.getHeight() + 4) * super.aStringArray175.length;
        }
        else {
            super.aStringArray175 = null;
        }
        if (this.anImage427 != null) {
            n += this.anImage427.getHeight() + 4;
        }
        super.anIntArray21[3] = Math.min(n, super.aScreen176.anIntArray21[3]);
    }
}
