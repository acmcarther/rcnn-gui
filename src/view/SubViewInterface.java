package view;

import java.awt.Container;

public interface SubViewInterface {
	public void initialize();
	public void addSubView(SubViewInterface subView);
	public Container getContainer();
}
