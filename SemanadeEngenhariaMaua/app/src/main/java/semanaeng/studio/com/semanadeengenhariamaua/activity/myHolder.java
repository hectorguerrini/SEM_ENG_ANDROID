package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import semanaeng.studio.com.semanadeengenhariamaua.R;

/**
 * Created by hecto on 14/06/2017.
 */

public class myHolder {
    TextView Curso;
    TextView Empresa;
    ImageView Image;

    public myHolder(View v) {
        Curso = (TextView) v.findViewById(R.id.texto2lista);
        Empresa = (TextView) v.findViewById(R.id.textolista);
        Image = (ImageView) v.findViewById(R.id.imagelista);
    }
}
