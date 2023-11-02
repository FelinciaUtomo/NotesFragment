package id.ac.petra.notesfragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    public static DetailFragment newInstance(int index){
        DetailFragment f = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("index",index);
        f.setArguments(args);
        return f;
    }

    public int getShowIndex (){
        return getArguments().getInt("index",0);
    }

//Generate --> Override Methods --> onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      return super.onCreateView(inflater, container, savedInstanceState);
        if(container==null){
            return null;
        }

        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,
                getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding,padding,padding,padding);
        scroller.addView(text);
        text.setText(Notes.NotesTitle[getShowIndex()]);
        return scroller;
    }
}
