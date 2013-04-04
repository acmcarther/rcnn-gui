package view;

import java.awt.Container;

public interface SubViewInterface {
	public void initialize();
	public void addSubView(SubViewInterface subView, String windowTitle);
	public Container getContainer();
}
