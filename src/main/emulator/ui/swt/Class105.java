package emulator.ui.swt;

import java.io.*;

final class Class105 implements FileFilter {
	Class105(final Property class38) {
		super();
	}

	public final boolean accept(final File file) {
		return file.isDirectory() && file.getName().startsWith(".");
	}
}
