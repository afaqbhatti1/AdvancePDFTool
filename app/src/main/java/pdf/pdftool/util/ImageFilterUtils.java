package pdf.pdftool.util;

import android.content.Context;

import java.util.ArrayList;

import ja.burhanrashid52.photoeditor.PhotoFilter;
import pdf.pdftool.R;
import pdf.pdftool.model.ItemFilter;

public class ImageFilterUtils {

    public ImageFilterUtils() {
    }

    private static class SingletonHolder {
        static final ImageFilterUtils INSTANCE = new ImageFilterUtils();
    }

    public static ImageFilterUtils getInstance() {
        return ImageFilterUtils.SingletonHolder.INSTANCE;
    }

    public ArrayList<ItemFilter> getFiltersList(Context context) {

        ArrayList<ItemFilter> items = new ArrayList<>();

        items.add(new ItemFilter(R.drawable.none,
                context.getString(R.string.filter_none), PhotoFilter.NONE));
        items.add(new ItemFilter(R.drawable.brush,
                context.getString(R.string.filter_brush), PhotoFilter.NONE));
        items.add(new ItemFilter(R.drawable.auto_fix,
                context.getString(R.string.filter_autofix), PhotoFilter.AUTO_FIX));
        items.add(new ItemFilter(R.drawable.black,
                context.getString(R.string.filter_grayscale), PhotoFilter.GRAY_SCALE));
        items.add(new ItemFilter(R.drawable.brightness,
                context.getString(R.string.filter_brightness), PhotoFilter.BRIGHTNESS));
        items.add(new ItemFilter(R.drawable.contrast,
                context.getString(R.string.filter_contrast), PhotoFilter.CONTRAST));
        items.add(new ItemFilter(R.drawable.cross_process,
                context.getString(R.string.filter_cross), PhotoFilter.CROSS_PROCESS));
        items.add(new ItemFilter(R.drawable.documentary,
                context.getString(R.string.filter_documentary), PhotoFilter.DOCUMENTARY));
        items.add(new ItemFilter(R.drawable.due_tone,
                context.getString(R.string.filter_duetone), PhotoFilter.DUE_TONE));
        items.add(new ItemFilter(R.drawable.fill_light,
                context.getString(R.string.filter_filllight), PhotoFilter.FILL_LIGHT));
        items.add(new ItemFilter(R.drawable.flip_vertical,
                context.getString(R.string.filter_filpver), PhotoFilter.FLIP_VERTICAL));
        items.add(new ItemFilter(R.drawable.flip_horizontal,
                context.getString(R.string.filter_fliphor), PhotoFilter.FLIP_HORIZONTAL));
        items.add(new ItemFilter(R.drawable.grain,
                context.getString(R.string.filter_grain), PhotoFilter.GRAIN));
        items.add(new ItemFilter(R.drawable.lomish,
                context.getString(R.string.filter_lomish), PhotoFilter.LOMISH));
        items.add(new ItemFilter(R.drawable.negative,
                context.getString(R.string.filter_negative), PhotoFilter.NEGATIVE));
        items.add(new ItemFilter(R.drawable.poster,
                context.getString(R.string.filter_poster), PhotoFilter.POSTERIZE));
        items.add(new ItemFilter(R.drawable.rotate,
                context.getString(R.string.filter_rotate), PhotoFilter.ROTATE));
        items.add(new ItemFilter(R.drawable.saturate,
                context.getString(R.string.filter_saturate), PhotoFilter.SATURATE));
        items.add(new ItemFilter(R.drawable.sepia,
                context.getString(R.string.filter_sepia), PhotoFilter.SEPIA));
        items.add(new ItemFilter(R.drawable.sharpen,
                context.getString(R.string.filter_sharpen), PhotoFilter.SHARPEN));
        items.add(new ItemFilter(R.drawable.temp,
                context.getString(R.string.filter_temp), PhotoFilter.TEMPERATURE));
        items.add(new ItemFilter(R.drawable.tint,
                context.getString(R.string.filter_tint), PhotoFilter.TINT));
        items.add(new ItemFilter(R.drawable.vignette,
                context.getString(R.string.filter_vig), PhotoFilter.VIGNETTE));

        return items;
    }
}
