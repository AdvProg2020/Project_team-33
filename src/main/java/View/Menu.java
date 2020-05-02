package View;

import Model.Manager;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String name;
    protected Menu previousMenu;
    protected ArrayList<Menu> subMenus;
    public static Scanner scanner;
    protected Manager manager;
    protected ArrayList<Menu> allMenus;

    public Menu(String name, Menu previousMenu) {
        this.name = name;
        this.previousMenu = previousMenu;
        subMenus = new ArrayList<Menu>();
        allMenus = new ArrayList<Menu>();
    }

    public void show(){

    }

    public String getName() {
        return name;
    }

    public void setSubMenus(ArrayList<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void execute(){

    }

    public void commandProcessor(){

    }

}
