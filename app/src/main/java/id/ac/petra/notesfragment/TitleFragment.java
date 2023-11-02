package id.ac.petra.notesfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class TitleFragment extends ListFragment {
    boolean mDualPane;
    int mCurrentPos = 0;

    //Generate --> Override Methods --> onActivityCreated
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,Notes.Notes));

        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame!=null &&
                detailsFrame.getVisibility() == View.VISIBLE;
        if (savedInstanceState!=null){
            mCurrentPos=savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurrentPos);
        }
    }

    //Generate --> Override Methods --> onListItemClick
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetails(position);
    }

    private void showDetails(int index){
        mCurrentPos = index;

        if (mDualPane){
//Landscape
        getListView().setItemChecked(index,true);
        DetailFragment detail = (DetailFragment)
                getFragmentManager().findFragmentById(R.id.details);
        if (detail==null || detail.getShowIndex() != index){
            detail = DetailFragment.newInstance(index);

            FragmentTransaction ft = getFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.details,detail)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
        }else {
//Portrait
            Intent i;
            i = new Intent();
            i.setClass(getActivity(),DetailActivity.class);
            i.putExtra("index",index);
            startActivity(i);
        }
    }

    //Generate --> Override Methods --> onSaveInstanceState
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",mCurrentPos);
    }
}
