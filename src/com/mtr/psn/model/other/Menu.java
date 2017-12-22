package com.mtr.psn.model.other;

import java.util.List;

import com.mtr.psn.model.user.Function;
/**
 * Menu
 * 菜单列表对象
 * @author lingd
 * @date 2017-11-28
 */
public class Menu{

	private Function mainMenu;//主菜单
	private List<Function> subMenus;//子菜单

	public List<Function> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Function> subMenus) {
		this.subMenus = subMenus;
	}

	public Function getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(Function mainMenu) {
		this.mainMenu = mainMenu;
	}

	

	
	
}
