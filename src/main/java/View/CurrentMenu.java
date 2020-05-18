package View;

public class CurrentMenu {
    public static Menu current;
    public static Menu getCurrent(){
        return current;
    }

    public static  void setMenu(Menu menu){
        current = menu;
    }
}
