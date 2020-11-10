package com.yat.helloworld.tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.yat.helloworld.R;

import java.util.Objects;

public class PDFActivity extends Fragment {

    PDFView agreementPDF;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_p_d_f, container, false);

        agreementPDF=v.findViewById(R.id.pdfView);


        agreementPDF.fromAsset("mobiletrack.pdf").load();


        return v;

    }


}