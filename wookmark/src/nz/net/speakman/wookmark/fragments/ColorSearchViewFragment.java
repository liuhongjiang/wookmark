package nz.net.speakman.wookmark.fragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
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

    // TODO Since this has to match the color of the rect, this should be stored in a resource somewhere
    private int mColor = 0xff000000;

    @Override
    public void setUri() {
        mUri = getString(R.string.wookmark_endpoint_color) + Uri.encode(getHexColor());
    }

    private String getHexColor() {
        String color = Integer.toHexString(mColor);
        if(color.length() == 8)
            color = color.substring(2);
        else
            color = "000000";
        return color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView != null) return mView;
        mView = inflater.inflate(R.layout.color_search_view, null);
        ColorPickerView cpv = (ColorPickerView)mView.findViewById(R.id.color_picker);
        cpv.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                GradientDrawable d = (GradientDrawable) getSherlockActivity().findViewById(R.id.color_picker_chosen_color_view).getBackground();
                d.setColor(color);
                d.invalidateSelf();
                mColor = color;
            }
        });
        // Will auto-restore the selected color after rotation, as mColor is saved by the OS.
        cpv.setColor(mColor);
        ((GradientDrawable)mView.findViewById(R.id.color_picker_chosen_color_view).getBackground()).setColor(mColor);

        Button b = (Button)mView.findViewById(R.id.colorSearchButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUri();
                showImagesView(true);
                getNewImages();
            }
        });
        AntipodalWallLayout awl = (AntipodalWallLayout)mView.findViewById(R.id.antipodal_wall);
        awl.setVisibility(View.GONE);
        return mView;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && imagesShown()) {
            showImagesView(false);
            return true;
        }
        return false;
    }

    private boolean imagesShown() {
        return getSherlockActivity().findViewById(R.id.antipodal_wall).getVisibility() == View.VISIBLE;
    }

    private void showImagesView(boolean showImages) {
        getSherlockActivity().findViewById(R.id.antipodal_wall).setVisibility(showImages ? View.VISIBLE : View.GONE);
        getSherlockActivity().findViewById(R.id.color_picker).setVisibility(showImages ? View.GONE : View.VISIBLE);
        getSherlockActivity().findViewById(R.id.color_picker_details).setVisibility(showImages ? View.GONE : View.VISIBLE);
    }

    @Override
    public String getTitle(Context ctx) {
        return ctx.getString(R.string.fragment_title_color_search);
    }
}
