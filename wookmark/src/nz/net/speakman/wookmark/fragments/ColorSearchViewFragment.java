package nz.net.speakman.wookmark.fragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.antipodalwall.AntipodalWallLayout;
import net.margaritov.preference.colorpicker.ColorPickerView;
import nz.net.speakman.wookmark.R;
import nz.net.speakman.wookmark.fragments.imageviewfragments.WookmarkBaseImageViewFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Adam
 * Date: 5/02/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ColorSearchViewFragment extends WookmarkBaseImageViewFragment {

    private int mColor;

    @Override
    public void setUri() {
        mUri = getString(R.string.wookmark_endpoint_color) + Uri.encode(getHexColor());
    }

    private String getHexColor() {
        return String.valueOf(mColor); // TODO
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_search_view, null);
        ColorPickerView cpv = (ColorPickerView)v.findViewById(R.id.color_picker);
        cpv.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                GradientDrawable d = (GradientDrawable) getSherlockActivity().findViewById(R.id.color_picker_chosen_color_view).getBackground();
                d.setColor(color);
                d.invalidateSelf();
                mColor = color;
            }
        });
        Button b = (Button)v.findViewById(R.id.colorSearchButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
//        AntipodalWallLayout awl = (AntipodalWallLayout)v.findViewById(R.id.antipodal_wall);
//        awl.setVisibility(View.GONE);
        return v;
    }

    @Override
    public String getTitle(Context ctx) {
        return ctx.getString(R.string.fragment_title_color_search);
    }
}