package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.soul.fileattente.R;

//public class EcranPrincipalActivityList extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ecran_principal_list);
//    }
//}


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

//https://www.javatpoint.com/android-recyclerview-list-example
public class EcranPrincipalActivityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_principal_list);

        ServiceListData[] serviceListData = new ServiceListData[] {
//                new ServiceListData("GYNECOLOGIE", android.R.drawable.ic_dialog_email),
//                new ServiceListData("PADIATRIE", android.R.drawable.ic_dialog_info),
//                new ServiceListData("LABORATOIRE", android.R.drawable.ic_delete),
//                new ServiceListData("RETRAIT DE RESULTAT", android.R.drawable.ic_dialog_dialer),
//                new ServiceListData("SERVICE_ON_0", android.R.drawable.ic_dialog_alert),
//                new ServiceListData("SERVICE_ON_1", android.R.drawable.ic_dialog_map),
//                new ServiceListData("SERVICE_ON_2", android.R.drawable.ic_dialog_email),
//                new ServiceListData("............", android.R.drawable.ic_dialog_info),
//                new ServiceListData("............", android.R.drawable.ic_delete),
//                new ServiceListData("............", android.R.drawable.ic_dialog_dialer),
//                new ServiceListData("............", android.R.drawable.ic_dialog_alert),
//                new ServiceListData("............", android.R.drawable.ic_dialog_map),

                new ServiceListData("GYNECOLOGIE", R.drawable.ic_baseline_timer_24),
                new ServiceListData("PADIATRIE", R.drawable.ic_baseline_timer_24),
                new ServiceListData("LABORATOIRE", R.drawable.ic_baseline_timer_24),
                new ServiceListData("RETRAIT DE RESULTAT", R.drawable.ic_baseline_timer_24),
                new ServiceListData("SERVICE_ON_0", R.drawable.ic_baseline_timer_24),
                new ServiceListData("SERVICE_ON_1",R.drawable.ic_baseline_timer_24),
                new ServiceListData("SERVICE_ON_2", R.drawable.ic_baseline_timer_24),
                new ServiceListData("............", R.drawable.ic_baseline_timer_24),
                new ServiceListData("............", R.drawable.ic_baseline_timer_24),
                new ServiceListData("............", R.drawable.ic_baseline_timer_24),
                new ServiceListData("............", R.drawable.ic_baseline_timer_24),
                new ServiceListData("............", R.drawable.ic_baseline_timer_24),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ServiceListDataAdapter adapter = new ServiceListDataAdapter(serviceListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}