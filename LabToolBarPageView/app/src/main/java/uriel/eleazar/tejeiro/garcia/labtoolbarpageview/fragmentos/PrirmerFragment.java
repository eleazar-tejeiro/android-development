package uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.R;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.interfaces.IAdcionarPersona;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.modelos.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrirmerFragment extends Fragment {
    EditText txtCi;
    EditText txtNombre;
    Button btnAdicionarPersona;
    IAdcionarPersona iAdcionarPersona;

    public PrirmerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_prirmer, container, false);
        txtCi = view.findViewById(R.id.editTextCI);
        txtNombre = view.findViewById(R.id.editTextNombre);
        btnAdicionarPersona = view.findViewById(R.id.buttonAdicionar);
        btnAdicionarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPersona_click();
            }
        });
        return view;
    }

    private void adicionarPersona_click() {
        Persona persona = new Persona(txtCi.getText().toString(),txtNombre.getText().toString());
        iAdcionarPersona.onAdicionarPersona(persona);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iAdcionarPersona = (IAdcionarPersona) context;
    }
}