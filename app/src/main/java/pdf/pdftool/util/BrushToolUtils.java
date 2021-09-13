package pdf.pdftool.util;

import java.util.ArrayList;

import pdf.pdftool.R;
import pdf.pdftool.model.ToolsItemBrush;

public class BrushToolUtils {

    private BrushToolUtils() {

    }

    public ArrayList<ToolsItemBrush> getBrushItems() {
        ArrayList<ToolsItemBrush> brushItems = new ArrayList<>();
        brushItems.add(new ToolsItemBrush(R.color.mb_white));
        brushItems.add(new ToolsItemBrush(R.color.red));
        brushItems.add(new ToolsItemBrush(R.color.mb_blue));
        brushItems.add(new ToolsItemBrush(R.color.mb_green));
        brushItems.add(new ToolsItemBrush(R.color.colorPrimary));
        brushItems.add(new ToolsItemBrush(R.color.colorAccent));
        brushItems.add(new ToolsItemBrush(R.color.light_gray));
        brushItems.add(new ToolsItemBrush(R.color.black));
        brushItems.add(new ToolsItemBrush(R.drawable.color_palette));
        return brushItems;
    }

    private static class SingletonHolder {
        static final BrushToolUtils INSTANCE = new BrushToolUtils();
    }

    public static BrushToolUtils getInstance() {
        return BrushToolUtils.SingletonHolder.INSTANCE;
    }
}