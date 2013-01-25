package nz.net.speakman.wookmark.fragments.basic;

import nz.net.speakman.wookmark.R;
import nz.net.speakman.wookmark.fragments.WookmarkBaseImageViewFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A basic view that displays images returned from the URI set by the subclass.
 * 
 * @author Adam Speakman
 * 
 */
public abstract class BasicViewFragment extends WookmarkBaseImageViewFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUri();
		// TODO Should this be here or somewhere else?
		if(savedInstanceState == null) // First-time load!
			refresh();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mView == null)
			mView = inflater.inflate(R.layout.basic_view, null, false);
		return mView;
	}

	public abstract void setUri();
	
}
