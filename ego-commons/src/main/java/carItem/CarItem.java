package carItem;

import com.ego.commons.ItemSolr;
import com.ego.pojo.TbItem;

public class CarItem {
    private TbItem tbItem;
    private int num;

    public TbItem getTbItem() {
        return this.tbItem;
    }

    public void setTbItem(final TbItem tbItem) {
        this.tbItem = tbItem;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(final int num) {
        this.num = num;
    }
}
