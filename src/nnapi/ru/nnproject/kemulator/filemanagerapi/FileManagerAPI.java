package ru.nnproject.kemulator.filemanagerapi;

import emulator.FileManagerImpl;

public class FileManagerAPI {

	public final static int JAVA = 0;
	public final static int SWING = 0;
	public final static int NATIVE = 1;

	public static AbstractFileManager getInstance(String title, int type) {
		return new FileManagerImpl(title, type);
	}
}
