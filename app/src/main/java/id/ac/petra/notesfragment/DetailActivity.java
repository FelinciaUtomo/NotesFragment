package id.ac.petra.notesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getResources().getConfiguration().orientation 
                == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        
        if (savedInstanceState==null){
            DetailFragment detail = new DetailFragment();
            detail.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,detail)
                    .commit();
        }
    }
}