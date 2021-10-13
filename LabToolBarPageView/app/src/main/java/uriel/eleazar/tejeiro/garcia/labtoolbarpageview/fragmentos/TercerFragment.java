package uriel.eleazar.tejeiro.garcia.labtoolbarpageview.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.R;
import uriel.eleazar.tejeiro.garcia.labtoolbarpageview.modelos.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class TercerFragment extends Fragment {
    Persona persona;
    TextView textViewPersona;

    public TercerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tercer, container, false);
        textViewPersona = view.findViewById(R.id.textViewPersona);
        if(persona != null)
            mostrarNuevaPersona(persona);
        return view;
    }

    public void mostrarNuevaPersona(Persona persona) {
        this.persona = persona;
        textViewPersona.setText(persona.getCi()+" "+persona.getNombre());
    }
}