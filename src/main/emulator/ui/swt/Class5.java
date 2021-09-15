package emulator.ui.swt;

import emulator.debug.*;
import java.util.*;
import java.util.List;
import java.lang.reflect.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.custom.*;

public final class Class5 implements Runnable, DisposeListener
{
    private Shell aShell544;
    private Shell aShell557;
    private Combo aCombo546;
    private Text aText543;
    private CLabel aCLabel547;
    private Button aButton549;
    private Button aButton558;
    private Display aDisplay550;
    private boolean aBoolean560;
    private Hashtable aHashtable552;
    private int anInt553;
    private Tree aTree554;
    private TreeEditor aTreeEditor555;
    private Class5 aClass5_556;
    private String aString551;
    public static Vector aVector548;
    private boolean aBoolean561;
    boolean aBoolean545;
    boolean aBoolean559;
    
    public Class5(final int anInt553) {
        super();
        this.aShell557 = null;
        this.aCombo546 = null;
        this.aText543 = null;
        this.aCLabel547 = null;
        this.aButton549 = null;
        this.aButton558 = null;
        this.aTree554 = null;
        this.aTreeEditor555 = null;
        this.aDisplay550 = Class146.getDisplay();
        this.anInt553 = anInt553;
        this.aHashtable552 = new Hashtable();
        this.aClass5_556 = this;
    }
    
    public Class5(final Object o) {
        super();
        this.aShell557 = null;
        this.aCombo546 = null;
        this.aText543 = null;
        this.aCLabel547 = null;
        this.aButton549 = null;
        this.aButton558 = null;
        this.aTree554 = null;
        this.aTreeEditor555 = null;
        if (o == null) {
            return;
        }
        this.aDisplay550 = Class146.getDisplay();
        this.anInt553 = 0;
        this.aHashtable552 = new Hashtable();
        final c c;
        (c = new c(o.getClass().getName(), o)).method879(null);
        this.aHashtable552.put(o.toString(), c);
        this.aClass5_556 = this;
    }
    
    public final void method302() {
        new Thread(new Class14(this)).start();
    }
    
    private void method322() {
        this.aString551 = this.aCombo546.getText();
        final c c;
        (c = (emulator.debug.c) this.aHashtable552.get(this.aString551)).method879(this.aButton549.getSelection() ? this.aText543.getText() : null);
        this.aTree554.removeAll();
        for (int i = 0; i < c.method881().size(); ++i) {
            final Object value;
            if ((value = c.method881().get(i)) instanceof Field) {
                final Field field = (Field) c.method881().get(i);
                final TreeItem treeItem;
                (treeItem = new TreeItem(this.aTree554, 0)).setText(0, field.getName());
                if (this.anInt553 == 0) {
                    this.aTree554.getItem(i).setText(2, ClassTypes.method869(field.getType()));
                    if (field.getType().isArray()) {
                        new TreeItem(treeItem, 0).setText(0, "");
                    }
                }
            }
            else {
                new TreeItem(this.aTree554, 0).setText(0, value.getClass().getName());
                if (this.anInt553 == 0) {
                    this.aTree554.getItem(i).setText(2, value.getClass().getName());
                }
            }
        }
    }
    
    private void method301(final c c, final Field field, final TreeItem treeItem) {
        final String method869 = ClassTypes.method869(field.getType());
        if (field.getType().isArray()) {
            final Object method870 = ClassTypes.method876(c.method882(), field);
            method305(method870, method303(method870, method869.substring(0, method869.length() - 2)), treeItem);
        }
    }
    
    private static String method303(final Object o, String method870) {
        if (method870.equalsIgnoreCase("java.lang.Object")) {
            try {
                final String string;
                final int index;
                if (Array.getLength(o) > 0 && (index = (string = Array.get(o, 0).toString()).indexOf(64)) != -1) {
                    method870 = ClassTypes.method870(string.substring(0, index));
                }
            }
            catch (Exception ex) {}
        }
        return method870;
    }
    
    private static void method305(final Object o, final String s, final TreeItem treeItem) {
        treeItem.removeAll();
        if (o != null) {
            for (int length = Array.getLength(o), i = 0; i < length; ++i) {
                final TreeItem treeItem2;
                (treeItem2 = new TreeItem(treeItem, 0)).setText(0, Integer.toString(i));
                treeItem2.setText(2, s);
                if (s.charAt(s.length() - 1) == ']') {
                    new TreeItem(treeItem2, 0).setText(0, "");
                }
            }
            return;
        }
        new TreeItem(treeItem, 0).setText(0, "");
    }
    
    private void method306(final TreeItem treeItem) {
        final c c = (emulator.debug.c) this.aHashtable552.get(this.aString551);
        if (treeItem.getParentItem() == null) {
            this.method301(c, (Field)c.method881().get(treeItem.getParent().indexOf(treeItem)), treeItem);
            return;
        }
        TreeItem parentItem = treeItem;
        final Stack stack = new Stack<TreeItem>();
        while (parentItem.getParentItem() != null) {
            stack.push(parentItem);
            parentItem = parentItem.getParentItem();
        }
        final Field field = (Field) c.method881().get(parentItem.getParent().indexOf(parentItem));
        Object o = ClassTypes.method876(c.method882(), field);
        final String method869 = ClassTypes.method869(field.getType());
        String s = method303(o, method869.substring(0, method869.length() - 2));
        String s2;
        while (true) {
            s2 = s;
            if (stack.isEmpty()) {
                break;
            }
            final TreeItem treeItem2 = (TreeItem) stack.pop();
            o = Array.get(o, treeItem2.getParentItem().indexOf(treeItem2));
            s = s2.substring(0, s2.length() - 2);
        }
        method305(o, s2, treeItem);
    }
    
    public final void method307(final TreeItem[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        final c c = (emulator.debug.c) this.aHashtable552.get(this.aString551);
        Object o;
        Class<?> clazz;
        if (array[0].getParentItem() == null) {
            final Field field = (Field) c.method881().get(array[0].getParent().indexOf(array[0]));
            o = ClassTypes.method876(c.method882(), field);
            clazz = field.getType();
        }
        else {
            TreeItem parentItem = array[0];
            final Stack stack = new Stack<TreeItem>();
            while (parentItem.getParentItem() != null) {
                stack.push(parentItem);
                parentItem = parentItem.getParentItem();
            }
            clazz = (o = ClassTypes.method876(c.method882(), (Field)c.method881().get(parentItem.getParent().indexOf(parentItem)))).getClass().getComponentType();
            while (!stack.isEmpty()) {
                final TreeItem treeItem = (TreeItem) stack.pop();
                final int index = treeItem.getParentItem().indexOf(treeItem);
                clazz = o.getClass().getComponentType();
                o = Array.get(o, index);
            }
        }
        if (o != null && ClassTypes.method871(clazz)) {
            new Class5(o).method311(this.aShell544);
        }
    }
    
    private void method320(final TreeItem[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        final TreeItem treeItem = array[0];
        final Control control;
        ((Text)(control = (Control)new Text((Composite)this.aTree554, 0))).setText(treeItem.getText(1));
        ((Text)control).selectAll();
        control.setFocus();
        control.addFocusListener((FocusListener)new Class18(this, treeItem, (Text)control));
        control.addKeyListener((KeyListener)new Class20(this, treeItem, (Text)control));
        this.aTreeEditor555.setEditor(control, treeItem, 1);
    }
    
    private void method310(final TreeItem treeItem, final String s) {
        final c c = (emulator.debug.c) this.aHashtable552.get(this.aString551);
        this.aBoolean545 = true;
        if (treeItem.getParentItem() == null) {
            ClassTypes.method875(c.method882(), (Field)c.method881().get(treeItem.getParent().indexOf(treeItem)), s);
        }
        else {
            TreeItem parentItem = treeItem;
            final Stack stack = new Stack<TreeItem>();
            while (parentItem.getParentItem() != null) {
                stack.push(parentItem);
                parentItem = parentItem.getParentItem();
            }
            int n = parentItem.getParent().indexOf(parentItem);
            Object o = ClassTypes.method876(c.method882(), (Field)c.method881().get(n));
            Object o2 = null;
        Label_0140:
            while (true) {
                o2 = o;
                while (!stack.isEmpty()) {
                    final TreeItem treeItem2 = (TreeItem) stack.pop();
                    n = treeItem2.getParentItem().indexOf(treeItem2);
                    if (!stack.isEmpty()) {
                        o = Array.get(o2, n);
                        continue Label_0140;
                    }
                }
                break;
            }
            if (o2 != null) {
                ClassTypes.method873(o2, n, s);
            }
        }
        this.aBoolean545 = false;
    }
    
    public final void method311(final Shell aShell544) {
        this.method324();
        this.method323();
            Shell shell = null;
            int n = 0;
            int y = 0;
            Label_0172: {
                switch (this.anInt553) {
                    case 0: {
                        ((Decorations)this.aShell557).setText(emulator.UILocale.uiText("WATCHES_FRAME_TITLE", "Class Watcher"));
                        if (aShell544 != null) {
                            break;
                        }
                        shell = this.aShell557;
                        n = this.aDisplay550.getClientArea().width - this.aShell557.getSize().x >> 1;
                        y = this.aDisplay550.getClientArea().height - this.aShell557.getSize().y >> 1;
                        break Label_0172;
                    }
                    case 1: {
                        ((Decorations)this.aShell557).setText(emulator.UILocale.uiText("WATCHES_FRAME_PROFILER", "Profiler Monitor"));
                        break;
                    }
                    default: {
                        break Label_0172;
                    }
                }
                ((Control)this.aShell557).setSize(aShell544.getSize());
                shell = this.aShell557;
                n = aShell544.getLocation().x - this.aShell557.getSize().x;
                y = aShell544.getLocation().y;
            }
            ((Control)shell).setLocation(n, y);
            this.aShell544 = aShell544;
            this.aShell557.open();
            ((Widget)this.aShell557).addDisposeListener((DisposeListener)this);
            this.aBoolean561 = false;
            this.aBoolean560 = true;
            Class146.asyncExec(this.aClass5_556);
            Class5.aVector548.addElement(this);
            while (!((Widget)this.aShell557).isDisposed()) {
                if (!this.aDisplay550.readAndDispatch()) {
                    this.aDisplay550.sleep();
                }
            }
            this.aBoolean560 = false;
            return;
        
    }
    
    public final void method321() {
        this.aBoolean561 = true;
        Class5.aVector548.removeElement(this);
        if (this.aShell557 != null && !((Widget)this.aShell557).isDisposed()) {
            this.aShell557.dispose();
        }
        this.aBoolean560 = false;
    }
    
    public final boolean method313() {
        return this.aBoolean560;
    }
    
    private void method323() {
        final List list;
        Collections.sort((List<Comparable>)(list = (List)Collections.list(this.aHashtable552.keys())));
        final Enumeration enumeration = Collections.enumeration((Collection)list);
        while (enumeration.hasMoreElements()) {
            this.aCombo546.add(enumeration.nextElement().toString());
        }
        Class5 class5;
        String item;
        if (this.aHashtable552.size() > 0) {
            class5 = this;
            item = this.aCombo546.getItem(0);
        }
        else {
            class5 = this;
            item = "";
        }
        class5.aString551 = item;
        this.aCombo546.setText(this.aString551);
        this.method322();
    }
    
    public final void run() {
        if (this.aHashtable552.size() == 0 || !this.aBoolean560 || this.aBoolean559 || this.aBoolean561) {
            return;
        }
        this.aBoolean559 = true;
        final c c = (emulator.debug.c) this.aHashtable552.get(this.aString551);
        int n = 0;
        try {
            for (int i = 0; i < c.method881().size(); ++i) {
                final Field field = (Field) c.method881().get(i);
                if (this.aBoolean561) {
                    this.aBoolean559 = false;
                    return;
                }
                final String s = (!Modifier.isStatic(field.getModifiers()) && c.method882() == null) ? "" : ClassTypes.method874(c.method882(), field, this.aButton558.getSelection());
                if (this.aBoolean545) {
                    this.aBoolean559 = false;
                    return;
                }
                final TreeItem item;
                (item = this.aTree554.getItem(n++)).setText(1, s);
                if (this.aBoolean545) {
                    this.aBoolean559 = false;
                    return;
                }
                this.method318(ClassTypes.method876(c.method882(), field), item);
            }
        }
        catch (Exception ex) {}
        this.aBoolean559 = false;
    }
    
    private void method318(final Object o, final TreeItem treeItem) {
        if (o == null) {
            return;
        }
        if (treeItem.getExpanded()) {
            for (int i = treeItem.getItemCount() - 1; i >= 0; --i) {
                final String method872 = ClassTypes.method872(o, i, this.aButton558.getSelection());
                final Object value = Array.get(o, i);
                final TreeItem item;
                (item = treeItem.getItem(i)).setText(1, method872);
                this.method318(value, item);
            }
        }
    }
    
    private void method324() {
        final GridData layoutData;
        (layoutData = new GridData()).horizontalAlignment = 4;
        layoutData.horizontalSpan = 5;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.grabExcessVerticalSpace = true;
        layoutData.verticalAlignment = 4;
        final GridData layoutData2;
        (layoutData2 = new GridData()).horizontalAlignment = 4;
        layoutData2.grabExcessHorizontalSpace = true;
        layoutData2.verticalAlignment = 2;
        final GridLayout layout;
        (layout = new GridLayout()).numColumns = 5;
        ((Decorations)(this.aShell557 = new Shell())).setText(emulator.UILocale.uiText("WATCHES_FRAME_TITLE", "Watches"));
        ((Decorations)this.aShell557).setImage(new Image((Device)Display.getCurrent(), this.getClass().getResourceAsStream("/res/icon")));
        ((Composite)this.aShell557).setLayout((Layout)layout);
        (this.aCLabel547 = new CLabel((Composite)this.aShell557, 0)).setText("Classes:");
        this.method325();
        ((Control)this.aShell557).setSize(new Point(351, 286));
        (this.aButton549 = new Button((Composite)this.aShell557, 32)).setText("Filter:");
        this.aButton549.addSelectionListener((SelectionListener)new Class139(this));
        ((Control)(this.aText543 = new Text((Composite)this.aShell557, 2048))).setLayoutData((Object)layoutData2);
        this.aText543.addModifyListener((ModifyListener)new Class141(this));
        (this.aButton558 = new Button((Composite)this.aShell557, 32)).setText("HEX");
        this.aButton558.addSelectionListener((SelectionListener)new Class8(this));
        (this.aTree554 = new Tree((Composite)this.aShell557, 67584)).setHeaderVisible(true);
        this.aTree554.setLinesVisible(true);
        ((Control)this.aTree554).setLayoutData((Object)layoutData);
        ((Control)this.aTree554).setToolTipText("Rigth click to open a Object Watcher");
        this.aTree554.addTreeListener((TreeListener)new Class6(this));
        ((Control)this.aTree554).addMouseListener((MouseListener)new Class12(this));
        final TreeColumn treeColumn;
        (treeColumn = new TreeColumn(this.aTree554, 16384)).setWidth(150);
        treeColumn.setText("Variable");
        treeColumn.setMoveable(true);
        final TreeColumn treeColumn2;
        (treeColumn2 = new TreeColumn(this.aTree554, 16384)).setWidth(150);
        treeColumn2.setText("Value");
        treeColumn2.setMoveable(true);
        this.aTreeEditor555 = new TreeEditor(this.aTree554);
        ((ControlEditor)this.aTreeEditor555).horizontalAlignment = 16384;
        ((ControlEditor)this.aTreeEditor555).grabHorizontal = true;
        if (this.anInt553 == 0) {
            final TreeColumn treeColumn3;
            (treeColumn3 = new TreeColumn(this.aTree554, 16384)).setWidth(150);
            treeColumn3.setText("Type");
            treeColumn3.setMoveable(true);
        }
    }
    
    private void method325() {
        final GridData layoutData;
        (layoutData = new GridData()).horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        layoutData.verticalAlignment = 2;
        ((Control)(this.aCombo546 = new Combo((Composite)this.aShell557, 8))).setLayoutData((Object)layoutData);
        aCombo546.setVisibleItemCount(8);
        this.aCombo546.addModifyListener((ModifyListener)new Class16(this));
    }
    
    public final void widgetDisposed(final DisposeEvent disposeEvent) {
        this.method321();
    }
    
    static int method314(final Class5 class5) {
        return class5.anInt553;
    }
    
    static Hashtable method304(final Class5 class5) {
        return class5.aHashtable552;
    }
    
    static void method315(final Class5 class5, final TreeItem treeItem, final String s) {
        class5.method310(treeItem, s);
    }
    
    static void method317(final Class5 class5) {
        class5.method322();
    }
    
    static Class5 method308(final Class5 class5) {
        return class5.aClass5_556;
    }
    
    static Button method312(final Class5 class5) {
        return class5.aButton549;
    }
    
    static void method316(final Class5 class5, final TreeItem treeItem) {
        class5.method306(treeItem);
    }
    
    static Tree method309(final Class5 class5) {
        return class5.aTree554;
    }
    
    static void method319(final Class5 class5, final TreeItem[] array) {
        class5.method320(array);
    }
    
    static {
        Class5.aVector548 = new Vector();
    }
}
