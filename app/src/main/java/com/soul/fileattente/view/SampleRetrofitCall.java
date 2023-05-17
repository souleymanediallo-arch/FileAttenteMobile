package com.soul.fileattente.view;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleRetrofitCall {
    private void getSuperHeroes() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();
                }

                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));

            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });


//                call.enqueue(new Callback<List<Results>>() {
//                    @Override
//                    public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
//                        List<Results> myheroList = response.body();
//                        String[] oneHeroes = new String[myheroList.size()];
//
//                        for (int i = 0; i < myheroList.size(); i++) {
//                            oneHeroes[i] = myheroList.get(i).getName();
//                        }
//
//                        superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Results>> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
//                    }
//
//                });
    }
}
