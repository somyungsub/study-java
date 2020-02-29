package design_pattern.chap09_iterator_composite.src;

import java.util.ArrayList;

public class PancakeHouseIterator implements Iterator {
    ArrayList<MenuItem> menuItems;
    int position = 0;

    public PancakeHouseIterator(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    // TODO 연습문제 : PancakeHouseIterator 적용 (p.365)
    @Override
    public boolean hasNext() {

        return menuItems != null && position < menuItems.size();
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems.get(position);
        position++;
        return menuItem;
    }
}
