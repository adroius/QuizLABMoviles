package com.example.quizlabmoviles;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPregunta5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPregunta5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPregunta5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPregunta5.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPregunta5 newInstance(String param1, String param2) {
        FragmentPregunta5 fragment = new FragmentPregunta5();
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
        View view = inflater.inflate(R.layout.fragment_pregunta5, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);

        ArrayList<SpinnerItem> customList = new ArrayList<>();
        customList.add(new SpinnerItem(R.drawable.piiiica, "Seleccione"));
        customList.add(new SpinnerItem(R.drawable.tipo_acero, "Acero"));
        customList.add(new SpinnerItem(R.drawable.tipo_agua, "Agua"));
        customList.add(new SpinnerItem(R.drawable.tipo_siniestro, "Siniestro"));
        customList.add(new SpinnerItem(R.drawable.tipo_fuego, "Fuego"));
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(getContext(), customList);

        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Spinner spinner = getView().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0)
                    ((MainActivity) getActivity()).comprobar(getView());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}