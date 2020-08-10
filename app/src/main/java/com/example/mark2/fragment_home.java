package com.example.mark2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class fragment_home extends Fragment {

    private WeightViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        final TextView weightText  = rootView.findViewById(R.id.home_weight);
        final TextView lostText = rootView.findViewById(R.id.total_lost);

        vm = ViewModelProviders.of(this).get(WeightViewModel.class);
        vm.getAllWeights().observe(this, new Observer<List<Weight>>() {
            @Override
            public void onChanged(@Nullable final List<Weight> weights) {
                // Update the cached copy of the words in the adapter.
                assert weights != null;
                if(weights.size()>0) {
                    weightText.setText(Integer.toString(weights.get(0).getWeight()));

                }else{
                    weightText.setText("N/A");
                }
                if(weights.size()>1){
                    lostText.setText(Integer.toString(weights.get(weights.size()-1).getWeight()-weights.get(0).getWeight()));

                }
                else{
                    lostText.setText("");
                }

            }
        });

        return rootView;
    }
}
