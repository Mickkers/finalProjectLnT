package com.mickkers.lntfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Volume#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Volume extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Volume() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Volume.
     */
    // TODO: Rename and change types and number of parameters
    public static Volume newInstance(String param1, String param2) {
        Volume fragment = new Volume();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volume, container, false);
    }

    private EditText textPanjang, textLebar, textTinggi;
    private TextView textResult, textAnswer;
    private RadioGroup radioGroup;
    private Button buttonCalculate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textPanjang = view.findViewById(R.id.textPanjangVolume);
        textLebar = view.findViewById(R.id.textLebarVolume);
        textTinggi = view.findViewById(R.id.textTinggiVolume);
        textResult = view.findViewById(R.id.textResultVolume);
        textAnswer = view.findViewById(R.id.textAnswerVolume);
        radioGroup = view.findViewById(R.id.radioGroupVolume);
        buttonCalculate = view.findViewById(R.id.buttonCalculateVolume);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int radioSelection = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = radioGroup.findViewById(radioSelection);

            if(radioButton.getText().toString().equals("Tabung")){
                textLebar.setVisibility(View.GONE);
                textPanjang.setHint("Radius");
            } else {
                textLebar.setVisibility(View.VISIBLE);
                textPanjang.setHint("Panjang");
            }
        });

        buttonCalculate.setOnClickListener(view1 -> {
            int radioSelection = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = radioGroup.findViewById(radioSelection);
            Double panjang = Double.valueOf(textPanjang.getText().toString());
            Double lebar = Double.valueOf(textLebar.getText().toString());
            Double tinggi = Double.valueOf(textTinggi.getText().toString());
            double result = 0;

            if(radioSelection == -1){
                Toast.makeText(getActivity().getApplicationContext(), "Please select a shape", Toast.LENGTH_SHORT).show();
            } else {
                if(radioButton.getText().toString().equals("Balok")){
                    result = panjang * lebar * tinggi;
                } else if(radioButton.getText().toString().equals("Piramid")){
                    result = (panjang * lebar * tinggi) / 3;
                } else if(radioButton.getText().toString().equals("Tabung")){
                    result = Math.PI * Math.pow(panjang, 2) * tinggi;
                }

                textAnswer.setText("Result:");
                textResult.setText(Double.toString(result));
            }
        });

    }
}